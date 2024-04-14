package com.eloinavarro.holocron.data

import com.eloinavarro.holocron.data.retrofit.SwapiRetrofitDatasource
import com.eloinavarro.holocron.domain.SWCharacter

class SWCharacterRepository constructor(private val apiDatasource: SwapiRetrofitDatasource) {

    suspend fun getAllCharacters(page: Int): Result<List<SWCharacter>> {
        return apiDatasource.getAllCharacters(page)
    }

    suspend fun getCharacterById(id: Int): Result<SWCharacter> {
        return apiDatasource.getCharacterById(id)
    }
}