package com.eloinavarro.holocron.ui.list

import com.eloinavarro.holocron.domain.SWCharacter

interface CharacterGridContract {
    interface View {
        fun populateCharacters(swCharacters: List<SWCharacter>)
    }

    interface Actions {
        fun setup()
        fun onCharacterTapped(swCharacters: SWCharacter)
        fun endOfPageReached()
    }
}