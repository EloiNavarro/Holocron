package com.eloinavarro.holocron.data.repositories

import com.eloinavarro.holocron.data.retrofit.SwapiRetrofitDatasource
import com.eloinavarro.holocron.domain.SWVehicle

class SWVehicleRepository constructor(private val apiDatasource: SwapiRetrofitDatasource) :
    Repository<SWVehicle>() {

    suspend fun getAllVehicles(page: Int): Result<List<SWVehicle>> {
        return apiDatasource.getAllVehicles(page)
    }

    suspend fun getVehicleById(id: Int): Result<SWVehicle> {
        return apiDatasource.getVehicleById(id)
    }
}