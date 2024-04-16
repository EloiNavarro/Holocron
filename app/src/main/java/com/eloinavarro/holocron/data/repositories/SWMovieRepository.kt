package com.eloinavarro.holocron.data.repositories

import com.eloinavarro.holocron.data.retrofit.SwapiRetrofitDatasource
import com.eloinavarro.holocron.domain.SWMovie
import com.eloinavarro.holocron.domain.SWPlanet
import com.eloinavarro.holocron.domain.SWStarship
import com.eloinavarro.holocron.domain.SwLinkType

class SWMovieRepository constructor(private val apiDatasource: SwapiRetrofitDatasource) :
    Repository<SWMovie>() {

    suspend fun getAllMovies(page: Int): Result<List<SWMovie>> {
        return apiDatasource.getAllMovies(page)
    }

    suspend fun getMovieById(id: Int): Result<SWMovie> {
        return apiDatasource.getMovieById(id)
    }
}