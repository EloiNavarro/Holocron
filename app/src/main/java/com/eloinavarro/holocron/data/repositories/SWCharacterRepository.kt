package com.eloinavarro.holocron.data.repositories

import com.eloinavarro.holocron.data.Datasource
import com.eloinavarro.holocron.data.StorageDatasource
import com.eloinavarro.holocron.domain.SWCharacter

class SWCharacterRepository (private val datasource: Datasource, private val cache: StorageDatasource) :
    Repository<SWCharacter>() {

    suspend fun getAllCharacters(page: Int): Result<List<SWCharacter>> = super.getAll(page) {
        val result = cache.getAllCharacters(page)
        if(result.isSuccess) {
            return@getAll result
        } else {
            datasource.getAllCharacters(page)
        }
    }

    suspend fun getCharacterById(id: Int): Result<SWCharacter> = super.find(id) {
        datasource.getCharacterById(id)
    }
}
