package com.example.gotcharacters.core.util

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RomanNumeralConverterTest {

    @Test
    fun toRoman_convertsBasicValuesCorrectly() {
        assertThat(RomanNumeralConverter.toRoman(1)).isEqualTo("I")
        assertThat(RomanNumeralConverter.toRoman(2)).isEqualTo("II")
        assertThat(RomanNumeralConverter.toRoman(4)).isEqualTo("IV")
        assertThat(RomanNumeralConverter.toRoman(5)).isEqualTo("V")
        assertThat(RomanNumeralConverter.toRoman(6)).isEqualTo("VI")
    }
}