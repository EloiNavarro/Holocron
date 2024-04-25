package com.eloinavarro.holocron.data.retrofit.services

import com.eloinavarro.holocron.data.retrofit.model.SwapiResponse
import com.eloinavarro.holocron.data.retrofit.model.SwapiStarship
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StarshipsService {
    @GET("starships")
    suspend fun getAllStarships(
        @Query("page") page: Int = 1
    ): Response<SwapiResponse<SwapiStarship>>

    @GET("starships/{id}")
    suspend fun getStarshipById(@Path("id") id: Int): Response<SwapiStarship>
}