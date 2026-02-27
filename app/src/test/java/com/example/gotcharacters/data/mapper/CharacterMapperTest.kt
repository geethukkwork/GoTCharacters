package com.example.gotcharacters.data.mapper

import com.example.gotcharacters.data.remote.dto.CharacterDto
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class CharacterMapperTest {

    @Test
    fun toDomain_mapsRealApiFieldsCorrectly() {
        val dto = CharacterDto(
            name = "Eddard Stark",
            gender = "Male",
            culture = "Northmen",
            born = "In 263 AC, at Winterfell",
            died = "In 299 AC, at Great Sept of Baelor in King's Landing",
            titles = listOf(
                "Lord of Winterfell",
                "Warden of the North"
            ),
            aliases = listOf(
                "Ned",
                "The Ned",
                "The Quiet Wolf"
            ),
            tvSeries = listOf("Season 1", "Season 6"),
            playedBy = listOf("Sean Bean", "Sebastian Croft")
        )

        val result = dto.toDomain(index = 0)

        assertThat(result.localId).isEqualTo(0)
        assertThat(result.stableKey).contains("character_0")
        assertThat(result.fullName).isEqualTo("Eddard Stark")
        assertThat(result.culture).isEqualTo("Northmen")
        assertThat(result.born).isEqualTo("In 263 AC, at Winterfell")
        assertThat(result.died).isEqualTo("In 299 AC, at Great Sept of Baelor in King's Landing")
        assertThat(result.title).isEqualTo("Lord of Winterfell")
        assertThat(result.actor).isEqualTo("Sean Bean")
        assertThat(result.aliases).containsExactly("Ned", "The Ned", "The Quiet Wolf").inOrder()
        assertThat(result.seasons).containsExactly(1, 6).inOrder()
    }

    @Test
    fun toDomain_usesFallbacksForBlankFields() {
        val dto = CharacterDto(
            name = "",
            culture = "",
            born = "",
            died = "",
            titles = listOf(""),
            aliases = listOf(""),
            tvSeries = emptyList(),
            playedBy = listOf("")
        )

        val result = dto.toDomain(index = 1)

        assertThat(result.fullName).isEqualTo("Unknown")
        assertThat(result.culture).isEqualTo("—")
        assertThat(result.born).isEqualTo("—")
        assertThat(result.died).isEqualTo("—")
        assertThat(result.title).isEqualTo("—")
        assertThat(result.actor).isEqualTo("—")
        assertThat(result.aliases).isEmpty()
        assertThat(result.seasons).isEmpty()
    }
}