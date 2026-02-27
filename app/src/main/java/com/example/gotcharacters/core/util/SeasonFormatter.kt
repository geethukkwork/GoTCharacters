package com.example.gotcharacters.core.util

object SeasonFormatter {
    fun format(seasons: List<Int>): String {
        if (seasons.isEmpty()) return "—"
        return seasons
            .distinct()
            .sorted()
            .joinToString(", ") { RomanNumeralConverter.toRoman(it) }
    }
}