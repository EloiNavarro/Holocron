package com.eloinavarro.holocron.data.repositories

import com.eloinavarro.holocron.data.retrofit.SwapiRetrofitDatasource
import com.eloinavarro.holocron.domain.SWPlanet

class SWPlanetRepository constructor(private val apiDatasource: SwapiRetrofitDatasource) :
    Repository<SWPlanet>() {

    suspend fun getAllPlanets(page: Int): Result<List<SWPlanet>> {
        return apiDatasource.getAllPlanets(page)
    }

    suspend fun getPlanetById(id: Int): Result<SWPlanet> {
        return apiDatasource.getPlanetById(id)
    }
}