package com.eloinavarro.holocron.ui.screens.vehicles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.eloinavarro.holocron.domain.SWVehicle
import com.eloinavarro.holocron.domain.usecase.GetVehicleByIdUseCase
import com.eloinavarro.holocron.ui.common.detail.DetailViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class VehicleDetailViewModel(
    override val id: Int
) : DetailViewModel<SWVehicle>(), KoinComponent {

    override val useCase: GetVehicleByIdUseCase by inject()

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

class VehicleDetailViewModelFactory(val id: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return VehicleDetailViewModel(id) as T
    }
}