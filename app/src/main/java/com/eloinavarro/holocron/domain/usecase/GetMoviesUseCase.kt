package com.eloinavarro.holocron.domain.usecase

import com.eloinavarro.holocron.data.repositories.SWMovieRepository
import com.eloinavarro.holocron.domain.SWMovie

class GetMoviesUseCase (private val repository: SWMovieRepository) : UseCase.Multiple<SWMovie> {
    override suspend operator fun invoke(page: Int): Result<List<SWMovie>> {
        return repository.getAllMovies(page)
    }
}