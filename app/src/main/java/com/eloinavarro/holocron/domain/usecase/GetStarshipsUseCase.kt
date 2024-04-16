package com.eloinavarro.holocron.domain.usecase

import com.eloinavarro.holocron.data.repositories.SWStarshipRepository
import com.eloinavarro.holocron.domain.SWStarship

class GetStarshipsUseCase(private val repository: SWStarshipRepository): UseCase.Multiple<SWStarship> {
    override suspend operator fun invoke(page: Int): Result<List<SWStarship>> {
        return repository.getAllStarships(page)
    }
}