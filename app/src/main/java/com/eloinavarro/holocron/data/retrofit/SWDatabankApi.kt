package com.eloinavarro.holocron.data.retrofit

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SWDatabankApi {

    @GET("people")
    suspend fun getAllCharacters(
        @Query("page") page: Int = 1
    ): Response<SwapiResponse<SwapiCharacter>>

    @GET("people/{id}")
    suspend fun getCharacterById(@Path("id") id: Int): Response<SwapiCharacter>

    @GET("planets")
    suspend fun getAllPlanets(
        @Query("page") page: Int = 1
    ): Response<SwapiResponse<SwapiPlanet>>

    @GET("planets/{id}")
    suspend fun getPlanetById(@Path("id") id: Int): Response<SwapiPlanet>

    @GET("films")
    suspend fun getAllFilms(
        @Query("page") page: Int = 1
    ): Response<SwapiResponse<SwapiCharacter>>

    @GET("films/{id}")
    suspend fun getFilmById(@Path("id") id: Int): Response<SwapiPlanet>

    @GET("species")
    suspend fun getAllSpecies(
        @Query("page") page: Int = 1
    ): Response<SwapiResponse<SwapiCharacter>>

    @GET("species/{id}")
    suspend fun getSpecieById(@Path("id") id: Int): Response<SwapiPlanet>

    @GET("vehicles")
    suspend fun getAllVehicles(
        @Query("page") page: Int = 1
    ): Response<SwapiResponse<SwapiCharacter>>

    @GET("vehicles/{id}")
    suspend fun getVehicleById(@Path("id") id: Int): Response<SwapiPlanet>

    @GET("starships")
    suspend fun getAllStarships(
        @Query("page") page: Int = 1
    ): Response<SwapiResponse<SwapiCharacter>>

    @GET("starships/{id}")
    suspend fun getStarshipById(@Path("id") id: Int): Response<SwapiPlanet>
}