package com.eloinavarro.holocron.ui.screens.planets

import androidx.lifecycle.viewModelScope
import com.eloinavarro.holocron.domain.SWPlanet
import com.eloinavarro.holocron.domain.usecase.GetPlanetsUseCase
import com.eloinavarro.holocron.ui.common.list.ListViewModel
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PlanetListViewModel : ListViewModel<SWPlanet>(), KoinComponent {

    override val useCase: GetPlanetsUseCase by inject()

    init {
        viewModelScope.launch {
            loadNextPage()
        }
    }
}