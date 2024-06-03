package com.eloinavarro.holocron.domain.usecase

import com.eloinavarro.holocron.data.repositories.SWCharacterRepository
import com.eloinavarro.holocron.domain.SWCharacter

class GetCharacterByIdUseCase (private val repository: SWCharacterRepository): UseCase.Item<SWCharacter> {
    override suspend operator fun invoke(id: Int): Result<SWCharacter> {
        return repository.getCharacterById(id)
    }
}