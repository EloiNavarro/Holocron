package com.eloinavarro.holocron.data.retrofit

import com.eloinavarro.holocron.domain.SWCharacter
import com.eloinavarro.holocron.domain.SWPlanet

class SwapiRetrofitDatasource {

    private val url = "https://swapi.dev/api/"

    private fun getApi(): SWDatabankApi {
        return RetrofitClient.getRetrofit(url)!!.create(SWDatabankApi::class.java)
    }

    suspend fun getAllCharacters(page: Int): Result<List<SWCharacter>> {
        val response = getApi().getAllCharacters(page)
        return if(response.isSuccessful && response.body() != null) {
            Result.success(response.body()!!.results.map { it.toDomainModel() })
        } else {
            Result.failure(Exception("Error getAllCharacters"))
        }
    }

    suspend fun getCharacterById(id: Int):Result<SWCharacter> {
        val response = getApi().getCharacterById(id)
        return if(response.isSuccessful && response.body() != null) {
            Result.success(response.body()!!.toDomainModel())
        } else {
            Result.failure(Exception("Error getCharacterById($id)"))
        }
    }

    suspend fun getAllPlanets(page: Int): Result<List<SWPlanet>> {
        val response = getApi().getAllPlanets(page)
        return if(response.isSuccessful && response.body() != null) {
            Result.success(response.body()!!.results.map { it.toDomainModel() })
        } else {
            Result.failure(Exception("Error getAllPlanets"))
        }
    }

    suspend fun getPlanetById(id: Int):Result<SWPlanet> {
        val response = getApi().getPlanetById(id)
        return if(response.isSuccessful && response.body() != null) {
            Result.success(response.body()!!.toDomainModel())
        } else {
            Result.failure(Exception("Error getPlanetById($id)"))
        }
    }
}