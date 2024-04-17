package com.eloinavarro.holocron.data.retrofit.services

import com.eloinavarro.holocron.data.retrofit.SwapiResponse
import com.eloinavarro.holocron.data.retrofit.SwapiSpecie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SpeciesService {
    @GET("species")
    suspend fun getAllSpecies(
        @Query("page") page: Int = 1
    ): Response<SwapiResponse<SwapiSpecie>>

    @GET("species/{id}")
    suspend fun getSpecieById(@Path("id") id: Int): Response<SwapiSpecie>
}