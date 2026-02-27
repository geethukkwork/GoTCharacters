package com.example.gotcharacters.data.remote

import com.example.gotcharacters.data.remote.dto.CharacterDto
import retrofit2.http.GET

interface CharactersApi {
    @GET("characters")
    suspend fun getCharacters(): List<CharacterDto>
}