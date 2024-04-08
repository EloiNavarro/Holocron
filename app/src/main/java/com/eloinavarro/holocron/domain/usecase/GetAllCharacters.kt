package com.eloinavarro.holocron.domain.usecase

import android.util.Log
import com.eloinavarro.holocron.data.SWCharacterRepository
import com.eloinavarro.holocron.domain.SWCharacter

class GetAllCharacters constructor(private val repository: SWCharacterRepository) :
    UseCase<List<SWCharacter>>() {
    private var page: Int = 1
    private var limit: Int = 50

    override suspend fun executeOnBackground(): List<SWCharacter> {
        val result = runAsync {
            repository.getAllCharacters(page, limit)
        }
        val list = result.await()
        return list
    }

    fun page(page: Int): GetAllCharacters {
        this.page = page
        return this
    }

    fun limit(limit: Int): GetAllCharacters {
        this.limit = limit
        return this
    }
}