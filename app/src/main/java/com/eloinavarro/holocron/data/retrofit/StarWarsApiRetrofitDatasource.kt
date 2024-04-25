package com.eloinavarro.holocron.data.retrofit

import com.eloinavarro.holocron.data.toDomainModel
import com.eloinavarro.holocron.domain.SWCharacter

class StarWarsApiRetrofitDatasource {

    suspend fun getAllCharacters(page: Int): Result<List<SWCharacter>> {
        if(page > 1) return Result.success(emptyList())
        val response = StarWarsApiRetrofitClient.charactersService.getAllCharacters()
        val results = response.body()
        return if(response.isSuccessful && results != null) {
            Result.success(results.map { it.toDomainModel() })
        } else {
            Result.failure(Exception("Error getAllCharacters"))
        }
    }

    suspend fun getCharacterById(id: Int):Result<SWCharacter> {
        val response = StarWarsApiRetrofitClient.charactersService.getCharacterById(id)
        return if(response.isSuccessful && response.body() != null) {
            val successResult = response.body()!!.toDomainModel()
            Result.success(successResult)
        } else {
            Result.failure(Exception("Error getCharacterById($id)"))
        }
    }
}