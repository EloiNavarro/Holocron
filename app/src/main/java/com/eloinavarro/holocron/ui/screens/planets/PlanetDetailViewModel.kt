package com.eloinavarro.holocron.ui.screens.planets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.eloinavarro.holocron.data.repositories.SWPlanetRepository
import com.eloinavarro.holocron.data.retrofit.SwapiRetrofitDatasource
import com.eloinavarro.holocron.domain.SWPlanet
import com.eloinavarro.holocron.domain.usecase.GetPlanetByIdUseCase
import com.eloinavarro.holocron.ui.common.detail.DetailViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PlanetDetailViewModel(
    override val id: Int
) : DetailViewModel<SWPlanet>() {

    override val useCase = GetPlanetByIdUseCase(
        repository = SWPlanetRepository(
            apiDatasource = SwapiRetrofitDatasource()
        )
    )

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