package com.example.gotcharacters.domain.usecase

import com.example.gotcharacters.domain.model.Character
import com.example.gotcharacters.domain.repository.CharactersRepository
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val repository: CharactersRepository
) {
    suspend operator fun invoke(): List<Character> = repository.getCharacters()
}