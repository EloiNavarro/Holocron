package com.eloinavarro.holocron.domain.usecase

import com.eloinavarro.holocron.data.repositories.SWStarshipRepository
import com.eloinavarro.holocron.domain.SWStarship

class GetStarshipByIdUseCase (private val repository: SWStarshipRepository): UseCase.Item<SWStarship> {
    override suspend operator fun invoke(id: Int): Result<SWStarship> {
        return repository.getStarshipById(id)
    }
}