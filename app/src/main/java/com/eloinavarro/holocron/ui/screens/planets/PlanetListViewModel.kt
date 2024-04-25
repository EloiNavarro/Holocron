package com.eloinavarro.holocron.ui.screens.planets

import androidx.lifecycle.viewModelScope
import com.eloinavarro.holocron.data.repositories.SWCharacterRepository
import com.eloinavarro.holocron.data.repositories.SWPlanetRepository
import com.eloinavarro.holocron.data.retrofit.SwapiRetrofitDatasource
import com.eloinavarro.holocron.domain.SWCharacter
import com.eloinavarro.holocron.domain.SWPlanet
import com.eloinavarro.holocron.domain.usecase.GetCharactersUseCase
import com.eloinavarro.holocron.domain.usecase.GetPlanetsUseCase
import com.eloinavarro.holocron.ui.common.list.ListViewModel
import kotlinx.coroutines.launch

class PlanetListViewModel : ListViewModel<SWPlanet>() {

    //TODO: Add Hilt and change this
    override val useCase = GetPlanetsUseCase(
        repository = SWPlanetRepository(
            apiDatasource = SwapiRetrofitDatasource()
        )
    )

    init {
        viewModelScope.launch {
            loadNextPage()
        }
    }
}