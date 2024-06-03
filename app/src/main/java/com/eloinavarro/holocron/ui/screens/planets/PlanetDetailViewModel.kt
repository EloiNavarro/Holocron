package com.eloinavarro.holocron.ui.screens.planets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.eloinavarro.holocron.domain.SWPlanet
import com.eloinavarro.holocron.domain.usecase.GetPlanetByIdUseCase
import com.eloinavarro.holocron.ui.common.detail.DetailViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PlanetDetailViewModel(override val id: Int) : DetailViewModel<SWPlanet>(), KoinComponent {

    override val useCase: GetPlanetByIdUseCase by inject()

    init {
        viewModelScope.launch {
            uiStateFlow.update { it.copy(loading = true) }
            uiStateFlow.update {
                it.copy(
                    item = useCase(id),
                    loading = false
                )
            }
        }
    }
}

class PlanetDetailViewModelFactory(val id: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PlanetDetailViewModel(id) as T
    }
}