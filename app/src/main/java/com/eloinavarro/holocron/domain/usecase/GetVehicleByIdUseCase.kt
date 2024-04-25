package com.eloinavarro.holocron.domain.usecase

import com.eloinavarro.holocron.data.repositories.SWVehicleRepository
import com.eloinavarro.holocron.domain.SWVehicle

class GetVehicleByIdUseCase(private val repository: SWVehicleRepository) : UseCase.Item<SWVehicle> {
    override suspend operator fun invoke(id: Int): Result<SWVehicle> {
        return repository.getVehicleById(id)
    }
}