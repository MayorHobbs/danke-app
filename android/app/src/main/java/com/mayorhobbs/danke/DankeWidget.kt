package com.mayorhobbs.danke

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.*
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import android.content.Context

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

    Column(
        modifier = GlanceModifier
            .fillMaxSize()
            .background(Color(0xFF1A1A1A))
            .padding(16.dp)
    ) {
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
            modifier = GlanceModifier.fillMaxWidth()
        ) {
            Text(
                text = "☐",
                style = TextStyle(
                    fontSize = 20.sp,
                    color = ColorProvider(Color(0xFFF5F5F5))
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

        // Metadata section
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
    }
}