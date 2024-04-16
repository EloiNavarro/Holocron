package com.eloinavarro.holocron.domain.usecase

import com.eloinavarro.holocron.data.repositories.SWPlanetRepository
import com.eloinavarro.holocron.domain.SWPlanet

class GetPlanetByIdUseCase(private val repository: SWPlanetRepository): UseCase.Item<SWPlanet> {
    override suspend operator fun invoke(id: Int): Result<SWPlanet> {
        return repository.getPlanetById(id)
    }
}