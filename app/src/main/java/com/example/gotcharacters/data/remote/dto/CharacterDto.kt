package com.example.gotcharacters.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterDto(
    @Json(name = "name") val name: String? = null,
    @Json(name = "gender") val gender: String? = null,
    @Json(name = "culture") val culture: String? = null,
    @Json(name = "born") val born: String? = null,
    @Json(name = "died") val died: String? = null,
    @Json(name = "titles") val titles: List<String>? = null,
    @Json(name = "aliases") val aliases: List<String>? = null,
    @Json(name = "tvSeries") val tvSeries: List<String>? = null,
    @Json(name = "playedBy") val playedBy: List<String>? = null
)