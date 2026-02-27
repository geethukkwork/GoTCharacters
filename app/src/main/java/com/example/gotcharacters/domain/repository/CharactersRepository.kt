package com.example.gotcharacters.domain.repository

import com.example.gotcharacters.domain.model.Character

interface CharactersRepository {
    suspend fun getCharacters(): List<Character>
}