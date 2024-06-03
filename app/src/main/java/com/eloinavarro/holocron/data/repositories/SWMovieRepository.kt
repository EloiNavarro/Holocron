package com.eloinavarro.holocron.data.repositories

import com.eloinavarro.holocron.data.Datasource
import com.eloinavarro.holocron.data.StorageDatasource
import com.eloinavarro.holocron.domain.SWMovie

class SWMovieRepository (private val datasource: Datasource, private val cache: StorageDatasource) :
    Repository<SWMovie>() {

    suspend fun getAllMovies(page: Int): Result<List<SWMovie>> {
        return datasource.getAllMovies(page)
    }

    suspend fun getMovieById(id: Int): Result<SWMovie> {
        return datasource.getMovieById(id)
    }
}