package com.eloinavarro.holocron.data

import com.eloinavarro.holocron.data.retrofit.SwCharacterRetrofitDatasource
import com.eloinavarro.holocron.domain.SWCharacter

class SWCharacterRepository constructor(private val apiDatasource: SwCharacterRetrofitDatasource) {

    suspend fun getAllCharacters(page: Int, limit: Int): Result<List<SWCharacter>> {
        return Result.success(apiDatasource.getAllCharacters(page, limit))
    }

    suspend fun getCharacterById(id: String): Result<SWCharacter> {
        return Result.success(apiDatasource.getCharacterById(id))
    }
}