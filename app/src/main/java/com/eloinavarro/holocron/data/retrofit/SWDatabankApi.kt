package com.eloinavarro.holocron.data.retrofit

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SWDatabankApi {

    @GET("characters")
    suspend fun getAllCharacters(
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 50
    ): SwCharactersResult

    @GET("characters/{id}")
    suspend fun getCharacterById(@Path("id") id: String): ApiSwCharacter
}