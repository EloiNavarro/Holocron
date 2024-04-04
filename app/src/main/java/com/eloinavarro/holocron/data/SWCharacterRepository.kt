package com.eloinavarro.holocron.data

import com.eloinavarro.holocron.data.retrofit.SwCharacterRetrofitDatasource
import com.eloinavarro.holocron.domain.SWCharacter

class SWCharacterRepository constructor(private val apiDatasource: SwCharacterRetrofitDatasource) {

    suspend fun getAllCharacters(page: Int, limit: Int): List<SWCharacter> {
        return apiDatasource.getAllCharacters(page, limit)
    }
}