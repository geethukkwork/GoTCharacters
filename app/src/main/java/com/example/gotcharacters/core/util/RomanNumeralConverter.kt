package com.example.gotcharacters.core.util

object RomanNumeralConverter {
    private val numerals = listOf(
        1000 to "M",
        900 to "CM",
        500 to "D",
        400 to "CD",
        100 to "C",
        90 to "XC",
        50 to "L",
        40 to "XL",
        10 to "X",
        9 to "IX",
        5 to "V",
        4 to "IV",
        1 to "I"
    )

    fun toRoman(value: Int): String {
        require(value > 0) { "Roman numerals only support positive integers" }
        var number = value
        val builder = StringBuilder()

        numerals.forEach { (arabic, roman) ->
            while (number >= arabic) {
                builder.append(roman)
                number -= arabic
            }
        }
        return builder.toString()
    }
}