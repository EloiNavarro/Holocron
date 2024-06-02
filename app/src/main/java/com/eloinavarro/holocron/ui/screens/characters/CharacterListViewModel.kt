package com.eloinavarro.holocron.ui.screens.characters

import androidx.lifecycle.viewModelScope
import com.eloinavarro.holocron.data.repositories.SWCharacterRepository
import com.eloinavarro.holocron.data.retrofit.SwapiRetrofitDatasource
import com.eloinavarro.holocron.domain.SWCharacter
import com.eloinavarro.holocron.domain.usecase.GetCharactersUseCase
import com.eloinavarro.holocron.ui.common.list.ListViewModel
import kotlinx.coroutines.launch

class CharacterListViewModel : ListViewModel<SWCharacter>() {

    //TODO: Add Hilt and change this
    override val useCase = GetCharactersUseCase(
        repository = SWCharacterRepository(
            apiDatasource = SwapiRetrofitDatasource()
        )
    )

    init {
        viewModelScope.launch {
            loadNextPage()
        }
    }
}