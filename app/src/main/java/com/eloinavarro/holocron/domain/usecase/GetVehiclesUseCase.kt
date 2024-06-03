package com.eloinavarro.holocron.domain.usecase

import com.eloinavarro.holocron.data.repositories.SWVehicleRepository
import com.eloinavarro.holocron.domain.SWVehicle

class GetVehiclesUseCase (private val repository: SWVehicleRepository): UseCase.Multiple<SWVehicle> {
    override suspend operator fun invoke(page: Int): Result<List<SWVehicle>> {
        return repository.getAllVehicles(page)
    }
}