package com.eloinavarro.holocron.data.retrofit.services

import com.eloinavarro.holocron.data.retrofit.SwapiPlanet
import com.eloinavarro.holocron.data.retrofit.SwapiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PlanetsService {
    @GET("planets")
    suspend fun getAllPlanets(
        @Query("page") page: Int = 1
    ): Response<SwapiResponse<SwapiPlanet>>

    @GET("planets/{id}")
    suspend fun getPlanetById(@Path("id") id: Int): Response<SwapiPlanet>
}