package com.eloinavarro.holocron.ui.screens.characters

import androidx.lifecycle.viewModelScope
import com.eloinavarro.holocron.domain.SWCharacter
import com.eloinavarro.holocron.domain.usecase.GetCharactersUseCase
import com.eloinavarro.holocron.ui.common.list.ListViewModel
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CharacterListViewModel : ListViewModel<SWCharacter>(), KoinComponent {

    override val useCase: GetCharactersUseCase by inject()

    init {
        viewModelScope.launch {
            loadNextPage()
        }
    }
}