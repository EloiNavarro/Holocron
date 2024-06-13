package com.eloinavarro.holocron.data.repositories

import com.eloinavarro.holocron.data.Datasource
import com.eloinavarro.holocron.data.StorageDatasource
import com.eloinavarro.holocron.domain.SWVehicle

class SWVehicleRepository (private val datasource: Datasource, private val cache: StorageDatasource) {

    suspend fun getAllVehicles(page: Int): Result<List<SWVehicle>> {
        return datasource.getAllVehicles(page)
    }

    suspend fun getVehicleById(id: Int): Result<SWVehicle> {
        return datasource.getVehicleById(id)
    }
}