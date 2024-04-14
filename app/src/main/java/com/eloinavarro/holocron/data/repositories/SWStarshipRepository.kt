package com.eloinavarro.holocron.data.repositories

import com.eloinavarro.holocron.data.retrofit.SwapiRetrofitDatasource
import com.eloinavarro.holocron.domain.SWPlanet
import com.eloinavarro.holocron.domain.SWStarship

class SWStarshipRepository constructor(private val apiDatasource: SwapiRetrofitDatasource) :
    Repository<SWStarship>() {

    suspend fun getAllStarships(page: Int): Result<List<SWStarship>> {
        return apiDatasource.getAllStarships(page)
    }

    suspend fun getStarshipById(id: Int): Result<SWStarship> {
        return apiDatasource.getStarshipById(id)
    }
}