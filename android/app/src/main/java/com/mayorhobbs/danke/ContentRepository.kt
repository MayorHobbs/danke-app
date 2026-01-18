package com.mayorhobbs.danke

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object ContentRepository {
    private var allContent: List<DailyContent> = emptyList()

    fun loadContent(context: Context) {
        if (allContent.isEmpty()) {
            try {
                val json = context.assets.open("content.json").bufferedReader().use { it.readText() }
                val type = object : TypeToken<List<DailyContent>>() {}.type
                allContent = Gson().fromJson(json, type)
                println("✅ Loaded ${allContent.size} days of content")
            } catch (e: Exception) {
                println("❌ Failed to load content: ${e.message}")
            }
        }
    }

    fun getCurrentDayContent(context: Context): DailyContent {
        loadContent(context)
        if (allContent.isEmpty()) {
            return getDefaultContent()
        }
        val prefs = context.getSharedPreferences("danke_prefs", Context.MODE_PRIVATE)
        val currentDay = prefs.getInt("current_day", 1)
        val dayIndex = (currentDay - 1) % allContent.size
        return allContent.getOrNull(dayIndex) ?: getDefaultContent()
    }

    private fun getDefaultContent(): DailyContent = DailyContent(
        day = 1,
        quote = "Focus is the ability to say no to everything except what matters most.",
        quoteSource = "The Art of Focus",
        action = "Write down 3 things you're saying yes to that don't align with your goals.",
        actionDuration = "10 min",
        domain = "consciousness",
        difficulty = "beginner"
    )
}