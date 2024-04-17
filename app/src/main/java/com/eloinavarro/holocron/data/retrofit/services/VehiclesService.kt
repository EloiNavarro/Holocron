package com.eloinavarro.holocron.data.retrofit.services

import com.eloinavarro.holocron.data.retrofit.SwapiResponse
import com.eloinavarro.holocron.data.retrofit.SwapiVehicle
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface VehiclesService {
    @GET("vehicles")
    suspend fun getAllVehicles(
        @Query("page") page: Int = 1
    ): Response<SwapiResponse<SwapiVehicle>>

    @GET("vehicles/{id}")
    suspend fun getVehicleById(@Path("id") id: Int): Response<SwapiVehicle>
}