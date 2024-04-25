package com.eloinavarro.holocron.data.retrofit.services

import com.eloinavarro.holocron.data.retrofit.model.ApiCharacter
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface StarWarsApiCharactersService {

    @GET("GetAllCharacters.json")
    suspend fun getAllCharacters(): Response<List<ApiCharacter>>

    @GET("id/{id}.json")
    suspend fun getCharacterById(@Path("id") id: Int): Response<ApiCharacter>
}