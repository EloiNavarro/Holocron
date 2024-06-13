package com.eloinavarro.holocron.data.repositories

import com.eloinavarro.holocron.data.Datasource
import com.eloinavarro.holocron.data.StorageDatasource
import com.eloinavarro.holocron.domain.SWStarship

class SWStarshipRepository (private val datasource: Datasource, private val cache: StorageDatasource) {

    suspend fun getAllStarships(page: Int): Result<List<SWStarship>> {
        return datasource.getAllStarships(page)
    }

    suspend fun getStarshipById(id: Int): Result<SWStarship> {
        return datasource.getStarshipById(id)
    }
}