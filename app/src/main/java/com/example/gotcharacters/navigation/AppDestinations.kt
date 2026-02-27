package com.example.gotcharacters.navigation

sealed class AppDestinations(val route: String) {
    data object CharactersList : AppDestinations("characters_list")
    data object CharacterDetails : AppDestinations("character_details/{characterId}") {
        fun createRoute(characterId: Int): String = "character_details/$characterId"
    }
}