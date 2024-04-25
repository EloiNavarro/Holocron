package com.eloinavarro.holocron.domain.usecase

import com.eloinavarro.holocron.data.repositories.SWMovieRepository
import com.eloinavarro.holocron.domain.SWMovie

class GetMovieByIdUseCase(private val repository: SWMovieRepository) : UseCase.Item<SWMovie> {
    override suspend operator fun invoke(id: Int): Result<SWMovie> {
        return repository.getMovieById(id)
    }
}