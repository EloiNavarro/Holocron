package com.eloinavarro.holocron.domain.usecase

import com.eloinavarro.holocron.data.repositories.SWCharacterRepository
import com.eloinavarro.holocron.domain.SWCharacter

class GetCharactersUseCase(private val repository: SWCharacterRepository) :
    UseCase.Multiple<SWCharacter> {
    override suspend operator fun invoke(page: Int): Result<List<SWCharacter>> {
        return repository.getAllCharacters(page)
    }
}