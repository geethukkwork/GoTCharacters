package com.example.gotcharacters.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gotcharacters.domain.model.Character
import com.example.gotcharacters.domain.usecase.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class CharactersListViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(CharactersListUiState(isLoading = true))
    val uiState: StateFlow<CharactersListUiState> = _uiState.asStateFlow()

    init {
        loadCharacters()
    }

    fun loadCharacters() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true,
                    errorMessage = null
                )
            }

            runCatching { getCharactersUseCase() }
                .onSuccess { characters ->
                    _uiState.update { current ->
                        current.copy(
                            isLoading = false,
                            characters = characters,
                            filteredCharacters = filterCharacters(characters, current.query),
                            errorMessage = null
                        )
                    }
                }
                .onFailure { throwable ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = throwable.toReadableMessage()
                        )
                    }
                }
        }
    }

    fun onQueryChanged(query: String) {
        _uiState.update { current ->
            current.copy(
                query = query,
                filteredCharacters = filterCharacters(current.characters, query)
            )
        }
    }

    private fun filterCharacters(
        characters: List<Character>,
        query: String
    ): List<Character> {
        val normalized = query.trim().lowercase()
        if (normalized.isBlank()) return characters

        return characters.filter { character ->
            character.fullName.lowercase().contains(normalized) ||
                    character.culture.lowercase().contains(normalized) ||
                    character.actor.lowercase().contains(normalized)
        }
    }

    private fun Throwable.toReadableMessage(): String {
        return when (this) {
            is IOException -> "Network error. Please check your connection and try again."
            is HttpException -> "Server error (${code()}). Please try again."
            else -> "Something went wrong. Please try again."
        }
    }
}