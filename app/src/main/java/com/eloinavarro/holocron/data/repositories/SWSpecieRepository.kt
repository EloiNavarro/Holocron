package com.eloinavarro.holocron.data.repositories

import com.eloinavarro.holocron.data.Datasource
import com.eloinavarro.holocron.data.StorageDatasource
import com.eloinavarro.holocron.domain.SWSpecie

class SWSpecieRepository (private val datasource: Datasource, private val cache: StorageDatasource) :
    Repository<SWSpecie>() {

    suspend fun getAllSpecies(page: Int): Result<List<SWSpecie>> {
        return datasource.getAllSpecies(page)
    }

    suspend fun getSpecieById(id: Int): Result<SWSpecie> {
        return datasource.getSpecieById(id)
    }
}