package com.eloinavarro.holocron.data.retrofit.services

import com.eloinavarro.holocron.data.retrofit.SwapiCharacter
import com.eloinavarro.holocron.data.retrofit.SwapiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharactersService {
    @GET("people")
    suspend fun getAllCharacters(
        @Query("page") page: Int = 1
    ): Response<SwapiResponse<SwapiCharacter>>

    @GET("people/{id}")
    suspend fun getCharacterById(@Path("id") id: Int): Response<SwapiCharacter>
}