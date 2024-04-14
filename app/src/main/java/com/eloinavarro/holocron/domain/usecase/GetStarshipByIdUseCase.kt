package com.eloinavarro.holocron.domain.usecase

import com.eloinavarro.holocron.data.SWStarshipRepository
import com.eloinavarro.holocron.domain.SWStarship

class GetStarshipByIdUseCase(private val repository: SWStarshipRepository) {
    suspend operator fun invoke(id: Int): Result<SWStarship> {
        return repository.getStarshipById(id)
    }
}