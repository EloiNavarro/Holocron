package com.eloinavarro.holocron.data.retrofit

import com.eloinavarro.holocron.data.Mapper
import com.eloinavarro.holocron.domain.SWCharacter

class ApiSwCharacterMapper : Mapper<ApiSwCharacter, SWCharacter> {
    override fun map(input: ApiSwCharacter): SWCharacter {
        return SWCharacter(
            id = input._id,
            name = input.name,
            description = input.description,
            image = input.image
        )
    }
}