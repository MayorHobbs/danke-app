package com.mayorhobbs.danke

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.updateAll
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

object DayManager {
    private const val PREFS_NAME = "danke_prefs"
    private const val KEY_CURRENT_DAY = "current_day"
    private const val KEY_LAST_UPDATE = "last_update_date"

    fun getCurrentDay(context: Context): Int {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        checkAndUpdateDay(context) // Check if day should increment
        return prefs.getInt(KEY_CURRENT_DAY, 1)
    }

    fun incrementDay(context: Context) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val currentDay = prefs.getInt(KEY_CURRENT_DAY, 1)
        val newDay = if (currentDay >= 7) 1 else currentDay + 1 // Loop after Day 7

        prefs.edit()
            .putInt(KEY_CURRENT_DAY, newDay)
            .putString(KEY_LAST_UPDATE, getTodayString())
            .apply()

        println("✅ Day incremented: $currentDay → $newDay")

        // Update all widgets
        CoroutineScope(Dispatchers.Main).launch {
            DankeWidget().updateAll(context)
        }
    }

    private fun checkAndUpdateDay(context: Context) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val lastUpdate = prefs.getString(KEY_LAST_UPDATE, "")
        val today = getTodayString()

        if (lastUpdate != today) {
            // It's a new day! Increment
            incrementDay(context)
        }
    }

    private fun getTodayString(): String {
        val calendar = Calendar.getInstance()
        return "${calendar.get(Calendar.YEAR)}-${calendar.get(Calendar.MONTH)}-${calendar.get(Calendar.DAY_OF_MONTH)}"
    }

    fun scheduleMidnightUpdate(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, MidnightReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // Calculate next midnight
        val calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            add(Calendar.DAY_OF_YEAR, 1) // Tomorrow at midnight
        }

        // Set repeating alarm
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )

        println("✅ Midnight alarm scheduled for: ${calendar.time}")
    }
}

// Broadcast receiver that fires at midnight
class MidnightReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        println("🌙 Midnight! Incrementing day...")
        DayManager.incrementDay(context)
    }
}