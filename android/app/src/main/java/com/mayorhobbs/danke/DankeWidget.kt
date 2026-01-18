package com.mayorhobbs.danke

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.action.ActionParameters
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.action.ActionCallback
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.*
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider

class DankeWidget : GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            DankeWidgetContent(context)
        }
    }
}

@Composable
fun DankeWidgetContent(context: Context) {
    val content = ContentRepository.getCurrentDayContent(context)
    val prefs = context.getSharedPreferences("danke_prefs", Context.MODE_PRIVATE)
    val currentDay = DayManager.getCurrentDay(context)
    val completionKey = "completed_day_$currentDay"
    val isCompleted = prefs.getBoolean(completionKey, false)

    Column(
        modifier = GlanceModifier
            .fillMaxSize()
            .background(Color(0xFF1A1A1A))
            .padding(16.dp),
        verticalAlignment = Alignment.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logo in top-right corner
        Row(
            modifier = GlanceModifier.fillMaxWidth(),
            horizontalAlignment = Alignment.End
        ) {
            Image(
                provider = ImageProvider(R.drawable.logo_small),
                contentDescription = "Danke Logo",
                modifier = GlanceModifier.size(24.dp)
            )
        }

        Spacer(modifier = GlanceModifier.height(8.dp))

        // Quote section
        Text(
            text = content.quote,
            style = TextStyle(
                fontSize = 15.sp,
                color = ColorProvider(Color(0xFFE8DCC4))
            ),
            maxLines = 4
        )

        Spacer(modifier = GlanceModifier.height(12.dp))

        // Action section
        Row(
            verticalAlignment = Alignment.Top,
            modifier = GlanceModifier
                .fillMaxWidth()
                .clickable(actionRunCallback<ToggleCompletionCallback>())
        ) {
            Text(
                text = if (isCompleted) "☑" else "☐",
                style = TextStyle(
                    fontSize = 20.sp,
                    color = ColorProvider(if (isCompleted) Color(0xFF7FA66A) else Color(0xFFF5F5F5))
                )
            )

            Spacer(modifier = GlanceModifier.width(8.dp))

            Column {
                Text(
                    text = content.action,
                    style = TextStyle(
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium,
                        color = ColorProvider(Color(0xFFF5F5F5))
                    ),
                    maxLines = 3
                )

                Spacer(modifier = GlanceModifier.height(4.dp))

                Text(
                    text = "(${content.actionDuration})",
                    style = TextStyle(
                        fontSize = 11.sp,
                        color = ColorProvider(Color(0xFFA0A0A0))
                    )
                )
            }
        }

        Spacer(modifier = GlanceModifier.defaultWeight())

        // Metadata + Credit
        Column(
            modifier = GlanceModifier.fillMaxWidth()
        ) {
            // Domain tag + source
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = content.domain.replaceFirstChar { it.uppercase() },
                    style = TextStyle(
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        color = ColorProvider(Color(content.getDomainColor()))
                    ),
                    modifier = GlanceModifier
                        .background(Color(content.getDomainColor()).copy(alpha = 0.3f))
                        .padding(horizontal = 6.dp, vertical = 3.dp)
                )

                Spacer(modifier = GlanceModifier.width(4.dp))

                Text(
                    text = "• ${content.quoteSource}",
                    style = TextStyle(
                        fontSize = 11.sp,
                        color = ColorProvider(Color(0xFFA0A0A0))
                    ),
                    maxLines = 1
                )
            }

            Spacer(modifier = GlanceModifier.height(4.dp))

            // Credit footer
            Text(
                text = "Crafted by Mayor Hobbs. Inspired by Dan Koe's philosophy",
                style = TextStyle(
                    fontSize = 9.sp,
                    color = ColorProvider(Color(0xFFA0A0A0).copy(alpha = 0.5f)),
                    fontWeight = FontWeight.Normal
                )
            )
        }
    }
}

class ToggleCompletionCallback : ActionCallback {
    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters
    ) {
        val prefs = context.getSharedPreferences("danke_prefs", Context.MODE_PRIVATE)
        val currentDay = DayManager.getCurrentDay(context)
        val completionKey = "completed_day_$currentDay"
        val isCompleted = prefs.getBoolean(completionKey, false)
        prefs.edit().putBoolean(completionKey, !isCompleted).apply()
        DankeWidget().update(context, glanceId)
    }
}

class NextDayCallback : ActionCallback {
    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters
    ) {
        DayManager.incrementDay(context)
        DankeWidget().update(context, glanceId)
    }
}

private fun DailyContent.getDomainColor(): Long {
    return when (domain.lowercase()) {
        "body" -> 0xFFD3A3A3
        "mind" -> 0xFF87A3D3
        "soul" -> 0xFFD3C3A3
        "consciousness" -> 0xFFB3A3D3
        "clarity" -> 0xFFa3d3c3
        "energy" -> 0xFFd3a3c3
        else -> 0xFFCCCCCC
    }
}
