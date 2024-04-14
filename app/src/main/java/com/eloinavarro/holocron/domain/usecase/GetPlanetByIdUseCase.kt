package com.eloinavarro.holocron.domain.usecase

import com.eloinavarro.holocron.data.repositories.SWPlanetRepository
import com.eloinavarro.holocron.domain.SWPlanet

class GetPlanetByIdUseCase(private val repository: SWPlanetRepository) {
    suspend operator fun invoke(id: Int): Result<SWPlanet> {
        return repository.getPlanetById(id)
    }
}