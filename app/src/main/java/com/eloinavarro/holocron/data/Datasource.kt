package com.eloinavarro.holocron.data

import com.eloinavarro.holocron.domain.SWCharacter
import com.eloinavarro.holocron.domain.SWMovie
import com.eloinavarro.holocron.domain.SWPlanet
import com.eloinavarro.holocron.domain.SWSpecie
import com.eloinavarro.holocron.domain.SWStarship
import com.eloinavarro.holocron.domain.SWVehicle

interface Datasource {
    suspend fun getAllCharacters(page: Int): Result<List<SWCharacter>>
    suspend fun getCharacterById(id: Int):Result<SWCharacter>

    suspend fun getAllPlanets(page: Int): Result<List<SWPlanet>>
    suspend fun getPlanetById(id: Int):Result<SWPlanet>

    suspend fun getAllStarships(page: Int): Result<List<SWStarship>>
    suspend fun getStarshipById(id: Int):Result<SWStarship>

    suspend fun getAllVehicles(page: Int): Result<List<SWVehicle>>
    suspend fun getVehicleById(id: Int):Result<SWVehicle>

    suspend fun getAllSpecies(page: Int): Result<List<SWSpecie>>
    suspend fun getSpecieById(id: Int):Result<SWSpecie>

    suspend fun getAllMovies(page: Int): Result<List<SWMovie>>
    suspend fun getMovieById(id: Int):Result<SWMovie>
}