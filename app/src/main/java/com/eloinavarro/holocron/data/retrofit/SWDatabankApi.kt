package com.eloinavarro.holocron.data.retrofit

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SWDatabankApi {

    @GET("people")
    suspend fun getAllCharacters(
        @Query("page") page: Int = 1
    ): Response<SwapiResponse>

    @GET("people/{id}")
    suspend fun getCharacterById(@Path("id") id: Int): Response<SwapiCharacter>
}