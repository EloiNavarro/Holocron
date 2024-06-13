package com.eloinavarro.holocron.data

import com.eloinavarro.holocron.domain.SWCharacter
import com.eloinavarro.holocron.domain.SWMovie
import com.eloinavarro.holocron.domain.SWPlanet
import com.eloinavarro.holocron.domain.SWSpecie
import com.eloinavarro.holocron.domain.SWStarship
import com.eloinavarro.holocron.domain.SWVehicle

interface StorageDatasource : Datasource {
    suspend fun storeMovies(data: List<SWMovie>)
    suspend fun storeSpecies(data: List<SWSpecie>)
    suspend fun storePlanets(data: List<SWPlanet>)
    suspend fun storeVehicles(data: List<SWVehicle>)
    suspend fun storeStarships(data: List<SWStarship>)
    suspend fun storeCharacters(data: List<SWCharacter>)
}