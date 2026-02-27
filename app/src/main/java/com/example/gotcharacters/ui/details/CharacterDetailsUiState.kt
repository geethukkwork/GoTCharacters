package com.example.gotcharacters.ui.details

import com.example.gotcharacters.domain.model.Character

data class CharacterDetailsUiState(
    val isLoading: Boolean = true,
    val character: Character? = null,
    val errorMessage: String? = null
)