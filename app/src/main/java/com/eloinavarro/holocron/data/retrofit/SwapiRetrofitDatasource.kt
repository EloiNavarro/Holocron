package com.eloinavarro.holocron.data.retrofit

import com.eloinavarro.holocron.data.Datasource
import com.eloinavarro.holocron.data.toDomainModel
import com.eloinavarro.holocron.domain.SWCharacter
import com.eloinavarro.holocron.domain.SWMovie
import com.eloinavarro.holocron.domain.SWPlanet
import com.eloinavarro.holocron.domain.SWSpecie
import com.eloinavarro.holocron.domain.SWStarship
import com.eloinavarro.holocron.domain.SWVehicle
import com.eloinavarro.holocron.domain.SwLink
import com.eloinavarro.holocron.domain.SwLinkList
import com.eloinavarro.holocron.domain.SwLinkType
import android.util.Log

class SwapiRetrofitDatasource : Datasource {


    override suspend fun getAllCharacters(page: Int): Result<List<SWCharacter>> {
        val response = SwapiRetrofitClient.charactersService.getAllCharacters(page)
        return if (response.isSuccessful && response.body() != null) {
            Result.success(response.body()!!.results.map { it.toDomainModel() })
        } else {
            Result.failure(Exception("Error getAllCharacters"))
        }
    }

    override suspend fun getCharacterById(id: Int): Result<SWCharacter> {
        val response = SwapiRetrofitClient.charactersService.getCharacterById(id)
        return if (response.isSuccessful && response.body() != null) {
            val successResult = response.body()!!.toDomainModel()
            getDetails(successResult.links)
            Result.success(successResult)
        } else {
            Result.failure(Exception("Error getCharacterById($id)"))
        }
    }

    private suspend fun getCharacterNameById(id: Int): String {
        val response = SwapiRetrofitClient.charactersService.getCharacterById(id)
        return if (response.isSuccessful && response.body() != null) {
            response.body()!!.name
        } else {
            "Character not found"
        }
    }

    override suspend fun getAllPlanets(page: Int): Result<List<SWPlanet>> {
        val response = SwapiRetrofitClient.planetsService.getAllPlanets(page)
        return if (response.isSuccessful && response.body() != null) {
            Result.success(response.body()!!.results.map { it.toDomainModel() })
        } else {
            Result.failure(Exception("Error getAllPlanets"))
        }
    }

    override suspend fun getPlanetById(id: Int): Result<SWPlanet> {
        val response = SwapiRetrofitClient.planetsService.getPlanetById(id)
        return if (response.isSuccessful && response.body() != null) {
            val successResult = response.body()!!.toDomainModel()
            getDetails(successResult.links)
            Result.success(successResult)
        } else {
            Result.failure(Exception("Error getPlanetById($id)"))
        }
    }

    private suspend fun getPlanetNameById(id: Int): String {
        val response = SwapiRetrofitClient.planetsService.getPlanetById(id)
        return if (response.isSuccessful && response.body() != null) {
            response.body()!!.name
        } else {
            "Planet not found"
        }
    }

    override suspend fun getAllStarships(page: Int): Result<List<SWStarship>> {
        val response = SwapiRetrofitClient.starshipsService.getAllStarships(page)
        return if (response.isSuccessful && response.body() != null) {
            Result.success(response.body()!!.results.map { it.toDomainModel() })
        } else {
            Result.failure(Exception("Error getAllStarships"))
        }
    }

    override suspend fun getStarshipById(id: Int): Result<SWStarship> {
        val response = SwapiRetrofitClient.starshipsService.getStarshipById(id)
        return if (response.isSuccessful && response.body() != null) {
            val successResult = response.body()!!.toDomainModel()
            getDetails(successResult.links)
            Result.success(successResult)
        } else {
            Result.failure(Exception("Error getStarshipById($id)"))
        }
    }

    private suspend fun getStarshipNameById(id: Int): String {
        val response = SwapiRetrofitClient.starshipsService.getStarshipById(id)
        return if (response.isSuccessful && response.body() != null) {
            response.body()!!.name
        } else {
            "Starship not found"
        }
    }

    override suspend fun getAllVehicles(page: Int): Result<List<SWVehicle>> {
        val response = SwapiRetrofitClient.vehiclesService.getAllVehicles(page)
        return if (response.isSuccessful && response.body() != null) {
            Result.success(response.body()!!.results.map { it.toDomainModel() })
        } else {
            Result.failure(Exception("Error getAllVehicles"))
        }
    }

    override suspend fun getVehicleById(id: Int): Result<SWVehicle> {
        val response = SwapiRetrofitClient.vehiclesService.getVehicleById(id)
        return if (response.isSuccessful && response.body() != null) {
            val successResult = response.body()!!.toDomainModel()
            getDetails(successResult.links)
            Result.success(successResult)
        } else {
            Result.failure(Exception("Error getVehicleById($id)"))
        }
    }

    private suspend fun getVehicleNameById(id: Int): String {
        val response = SwapiRetrofitClient.vehiclesService.getVehicleById(id)
        return if (response.isSuccessful && response.body() != null) {
            response.body()!!.name
        } else {
            "Vehicle not found"
        }
    }

    override suspend fun getAllSpecies(page: Int): Result<List<SWSpecie>> {
        val response = SwapiRetrofitClient.speciesService.getAllSpecies(page)
        return if (response.isSuccessful && response.body() != null) {
            Result.success(response.body()!!.results.map { it.toDomainModel() })
        } else {
            Result.failure(Exception("Error getAllStarships"))
        }
    }

    override suspend fun getSpecieById(id: Int): Result<SWSpecie> {
        val response = SwapiRetrofitClient.speciesService.getSpecieById(id)
        return if (response.isSuccessful && response.body() != null) {
            val successResult = response.body()!!.toDomainModel()
            getDetails(successResult.links)
            Result.success(successResult)
        } else {
            Result.failure(Exception("Error getStarshipById($id)"))
        }
    }

    private suspend fun getSpecieNameById(id: Int): String {
        val response = SwapiRetrofitClient.speciesService.getSpecieById(id)
        return if (response.isSuccessful && response.body() != null) {
            response.body()!!.name
        } else {
            "Specie not found"
        }
    }

    override suspend fun getAllMovies(page: Int): Result<List<SWMovie>> {
        val response = SwapiRetrofitClient.moviesService.getAllMovies(page)
        return if (response.isSuccessful && response.body() != null) {
            Result.success(response.body()!!.results.map { it.toDomainModel() })
        } else {
            Result.failure(Exception("Error getAllStarships"))
        }
    }

    override suspend fun getMovieById(id: Int): Result<SWMovie> {
        val response = SwapiRetrofitClient.moviesService.getMovieById(id)
        return if (response.isSuccessful && response.body() != null) {
            val successResult = response.body()!!.toDomainModel()
            getDetails(successResult.links)
            Result.success(successResult)
        } else {
            Result.failure(Exception("Error getStarshipById($id)"))
        }
    }

    private suspend fun getMovieNameById(id: Int): String {
        val response = SwapiRetrofitClient.moviesService.getMovieById(id)
        return if (response.isSuccessful && response.body() != null) {
            response.body()!!.title
        } else {
            "Movie not found"
        }
    }

    private suspend fun getDetails(links: List<SwLinkList>) {
        Log.d("DEBUG", "getDetails: $links")
        links.map { swLinkList ->
            swLinkList.links.map { swLink ->
                swLink.name = detailsFetcher[swLinkList.type]!!.invoke(swLink)
            }
        }
    }

    private val detailsFetcher: Map<SwLinkType, suspend (SwLink) -> String> = mapOf(
        SwLinkType.PLANET to { getPlanetNameById(it.id) },
        SwLinkType.CHARACTER to { getCharacterNameById(it.id) },
        SwLinkType.SPECIE to { getSpecieNameById(it.id) },
        SwLinkType.VEHICLE to { getVehicleNameById(it.id) },
        SwLinkType.STARSHIP to { getStarshipNameById(it.id) },
        SwLinkType.MOVIE to { getMovieNameById(it.id) }
    )
}