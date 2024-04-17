package com.eloinavarro.holocron.data.retrofit.services

import com.eloinavarro.holocron.data.retrofit.SwapiMovie
import com.eloinavarro.holocron.data.retrofit.SwapiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesService {
    @GET("films")
    suspend fun getAllMovies(
        @Query("page") page: Int = 1
    ): Response<SwapiResponse<SwapiMovie>>

    @GET("films/{id}")
    suspend fun getMovieById(@Path("id") id: Int): Response<SwapiMovie>
}