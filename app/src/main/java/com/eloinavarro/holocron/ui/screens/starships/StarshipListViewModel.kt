package com.eloinavarro.holocron.ui.screens.starships

import androidx.lifecycle.viewModelScope
import com.eloinavarro.holocron.domain.SWStarship
import com.eloinavarro.holocron.domain.usecase.GetStarshipsUseCase
import com.eloinavarro.holocron.ui.common.list.ListViewModel
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class StarshipListViewModel : ListViewModel<SWStarship>(), KoinComponent {

    override val useCase: GetStarshipsUseCase by inject()

    init {
        viewModelScope.launch {
            loadNextPage()
        }
    }
}