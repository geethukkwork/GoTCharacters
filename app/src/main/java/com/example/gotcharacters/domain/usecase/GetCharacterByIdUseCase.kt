package com.example.gotcharacters.domain.usecase

import com.example.gotcharacters.domain.model.Character
import com.example.gotcharacters.domain.repository.CharactersRepository
import javax.inject.Inject

class GetCharacterByIdUseCase @Inject constructor(
    private val repository: CharactersRepository
) {
    suspend operator fun invoke(localId: Int): Character? {
        return repository.getCharacters().firstOrNull { it.localId == localId }
    }
}