package com.example.gotcharacters.data.repository

import com.example.gotcharacters.data.mapper.toDomain
import com.example.gotcharacters.data.remote.CharactersApi
import com.example.gotcharacters.domain.model.Character
import com.example.gotcharacters.domain.repository.CharactersRepository
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val api: CharactersApi
) : CharactersRepository {

    private var cachedCharacters: List<Character>? = null

    override suspend fun getCharacters(): List<Character> {
        cachedCharacters?.let { return it }

        val characters = api.getCharacters()
            .mapIndexed { index, dto -> dto.toDomain(index) }
            .sortedBy { it.fullName }

        cachedCharacters = characters
        return characters
    }
}