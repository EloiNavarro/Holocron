package com.eloinavarro.holocron.data.repositories

import com.eloinavarro.holocron.data.retrofit.SwapiRetrofitDatasource
import com.eloinavarro.holocron.domain.SWSpecie
import com.eloinavarro.holocron.domain.SWStarship

class SWSpecieRepository constructor(private val apiDatasource: SwapiRetrofitDatasource) :
    Repository<SWStarship>() {

    suspend fun getAllSpecies(page: Int): Result<List<SWSpecie>> {
        return apiDatasource.getAllSpecies(page)
    }

    suspend fun getSpecieById(id: Int): Result<SWSpecie> {
        return apiDatasource.getSpecieById(id)
    }
}