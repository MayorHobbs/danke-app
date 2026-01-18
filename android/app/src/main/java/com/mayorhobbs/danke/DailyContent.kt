package com.mayorhobbs.danke

data class DailyContent(
    val day: Int,
    val quote: String,
    val quoteSource: String,
    val action: String,
    val actionDuration: String,
    val domain: String,
    val difficulty: String
) {
    fun getDomainColor(): Long {
        return when (domain.lowercase()) {
            "consciousness" -> 0xFF8B7FB8
            "health" -> 0xFF7FA66A
            "wealth" -> 0xFFC9A961
            "relationships" -> 0xFFB87F8F
            else -> 0xFF8B7FB8
        }
    }
}