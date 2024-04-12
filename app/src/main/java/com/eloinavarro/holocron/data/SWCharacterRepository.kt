package com.eloinavarro.holocron.data

import com.eloinavarro.holocron.data.retrofit.SwCharacterRetrofitDatasource
import com.eloinavarro.holocron.domain.SWCharacter

class SWCharacterRepository constructor(private val apiDatasource: SwCharacterRetrofitDatasource) {

    suspend fun getAllCharacters(page: Int): Result<List<SWCharacter>> {
        return apiDatasource.getAllCharacters(page)
    }

    suspend fun getCharacterById(id: String): Result<SWCharacter> {
        return apiDatasource.getCharacterById(id)
    }
}