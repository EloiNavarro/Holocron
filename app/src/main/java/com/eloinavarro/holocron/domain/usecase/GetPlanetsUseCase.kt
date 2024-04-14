package com.eloinavarro.holocron.domain.usecase

import com.eloinavarro.holocron.data.repositories.SWPlanetRepository
import com.eloinavarro.holocron.domain.SWPlanet

class GetPlanetsUseCase(private val repository: SWPlanetRepository) {
    suspend operator fun invoke(page: Int): Result<List<SWPlanet>> {
        return repository.getAllPlanets(page)
    }
}