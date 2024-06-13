package com.eloinavarro.holocron.data.repositories

import com.eloinavarro.holocron.data.Datasource
import com.eloinavarro.holocron.data.StorageDatasource
import com.eloinavarro.holocron.domain.SWCharacter

class SWCharacterRepository(
    private val datasource: Datasource,
    private val cache: StorageDatasource
) {

    suspend fun getAllCharacters(page: Int): Result<List<SWCharacter>> {
        val result = cache.getAllCharacters(page)
        return if (result.isSuccess) {
            result
        } else {
            val data = datasource.getAllCharacters(page)
            data.onSuccess { cache.storeCharacters(it) }
            data
        }
    }

    suspend fun getCharacterById(id: Int): Result<SWCharacter> {
        val result = cache.getCharacterById(id)
        return if (result.isSuccess) {
            result
        } else {
            val data = datasource.getCharacterById(id)
            data.onSuccess { cache.storeCharacters(listOf(it)) }
            data
        }
    }
}
