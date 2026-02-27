package com.example.gotcharacters.ui.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gotcharacters.domain.usecase.GetCharacterByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(CharacterDetailsUiState())
    val uiState: StateFlow<CharacterDetailsUiState> = _uiState.asStateFlow()

    private val characterId: Int = checkNotNull(savedStateHandle["characterId"])

    init {
        loadCharacter()
    }

    private fun loadCharacter() {
        viewModelScope.launch {
            val character = getCharacterByIdUseCase(characterId)

            _uiState.update {
                if (character != null) {
                    it.copy(
                        isLoading = false,
                        character = character,
                        errorMessage = null
                    )
                } else {
                    it.copy(
                        isLoading = false,
                        character = null,
                        errorMessage = "Character not found"
                    )
                }
            }
        }
    }
}