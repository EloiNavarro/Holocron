package com.eloinavarro.holocron.ui.screens.vehicles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.eloinavarro.holocron.data.repositories.SWMovieRepository
import com.eloinavarro.holocron.data.repositories.SWVehicleRepository
import com.eloinavarro.holocron.data.retrofit.SwapiRetrofitDatasource
import com.eloinavarro.holocron.domain.SWMovie
import com.eloinavarro.holocron.domain.SWVehicle
import com.eloinavarro.holocron.domain.usecase.GetMovieByIdUseCase
import com.eloinavarro.holocron.domain.usecase.GetVehicleByIdUseCase
import com.eloinavarro.holocron.ui.common.detail.DetailViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class VehicleDetailViewModel(
    override val id: Int
) : DetailViewModel<SWVehicle>() {

    override val useCase = GetVehicleByIdUseCase(
        repository = SWVehicleRepository(
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

class VehicleDetailViewModelFactory(val id: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return VehicleDetailViewModel(id) as T
    }
}