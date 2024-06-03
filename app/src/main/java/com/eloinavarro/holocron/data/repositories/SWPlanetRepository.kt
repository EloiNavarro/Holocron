package com.eloinavarro.holocron.data.repositories

import com.eloinavarro.holocron.data.Datasource
import com.eloinavarro.holocron.data.StorageDatasource
import com.eloinavarro.holocron.domain.SWPlanet

class SWPlanetRepository (private val datasource: Datasource, private val cache: StorageDatasource) :
    Repository<SWPlanet>() {

    suspend fun getAllPlanets(page: Int): Result<List<SWPlanet>> {
        return datasource.getAllPlanets(page)
    }

    suspend fun getPlanetById(id: Int): Result<SWPlanet> {
        return datasource.getPlanetById(id)
    }
}