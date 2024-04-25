package com.eloinavarro.holocron.data.repositories

import com.eloinavarro.holocron.data.retrofit.StarWarsApiRetrofitDatasource
import com.eloinavarro.holocron.data.retrofit.SwapiRetrofitDatasource
import com.eloinavarro.holocron.domain.SWCharacter

class SWCharacterRepository constructor(private val apiDatasource: StarWarsApiRetrofitDatasource) :
    Repository<SWCharacter>() {

    suspend fun getAllCharacters(page: Int): Result<List<SWCharacter>> = super.getAll(page) {
        apiDatasource.getAllCharacters(page)
    }

    suspend fun getCharacterById(id: Int): Result<SWCharacter> = super.find(id) {
        apiDatasource.getCharacterById(id)
    }
}
