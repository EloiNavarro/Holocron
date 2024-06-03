package com.eloinavarro.holocron.data

import com.eloinavarro.holocron.domain.SWCharacter
import com.eloinavarro.holocron.domain.SWMovie
import com.eloinavarro.holocron.domain.SWPlanet
import com.eloinavarro.holocron.domain.SWSpecie
import com.eloinavarro.holocron.domain.SWStarship
import com.eloinavarro.holocron.domain.SWVehicle

interface StorageDatasource : Datasource {
    suspend fun storeMovies(movies: List<SWMovie>)
    suspend fun storeSpecies(species: List<SWSpecie>)
    suspend fun storePlanets(planets: List<SWPlanet>)
    suspend fun storeVehicles(vehicles: List<SWVehicle>)
    suspend fun storeStarships(starships: List<SWStarship>)
    suspend fun storeCharacters(characters: List<SWCharacter>)
}