package com.eloinavarro.holocron.ui.screens.vehicles

import androidx.lifecycle.viewModelScope
import com.eloinavarro.holocron.domain.SWVehicle
import com.eloinavarro.holocron.domain.usecase.GetVehiclesUseCase
import com.eloinavarro.holocron.ui.common.list.ListViewModel
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class VehicleListViewModel() : ListViewModel<SWVehicle>(), KoinComponent {

    override val useCase: GetVehiclesUseCase by inject()

    init {
        viewModelScope.launch {
            loadNextPage()
        }
    }
}