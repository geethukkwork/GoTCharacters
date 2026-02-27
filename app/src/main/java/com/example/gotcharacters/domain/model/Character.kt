package com.example.gotcharacters.domain.model

data class Character(
    val localId: Int,
    val stableKey: String,
    val fullName: String,
    val culture: String,
    val born: String,
    val died: String,
    val title: String,
    val actor: String,
    val aliases: List<String>,
    val seasons: List<Int>
)