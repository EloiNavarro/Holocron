package com.eloinavarro.holocron.ui.screens.starships

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.eloinavarro.holocron.domain.SWStarship
import com.eloinavarro.holocron.domain.usecase.GetStarshipByIdUseCase
import com.eloinavarro.holocron.ui.common.detail.DetailViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class StarshipDetailViewModel(
    override val id: Int
) : DetailViewModel<SWStarship>(), KoinComponent {

    override val useCase: GetStarshipByIdUseCase by inject()

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

class StarshipDetailViewModelFactory(val id: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return StarshipDetailViewModel(id) as T
    }
}