package com.example.gotcharacters.ui.list

import com.example.gotcharacters.domain.model.Character

data class CharactersListUiState(
    val isLoading: Boolean = false,
    val query: String = "",
    val characters: List<Character> = emptyList(),
    val filteredCharacters: List<Character> = emptyList(),
    val errorMessage: String? = null
)