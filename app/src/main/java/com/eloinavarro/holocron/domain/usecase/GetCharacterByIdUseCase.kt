package com.eloinavarro.holocron.domain.usecase

import com.eloinavarro.holocron.data.SWCharacterRepository
import com.eloinavarro.holocron.domain.SWCharacter

class GetCharacterByIdUseCase(private val repository: SWCharacterRepository) {
    suspend operator fun invoke(id: Int): Result<SWCharacter> {
        return repository.getCharacterById(id)
    }
}