package com.eloinavarro.holocron.ui.screens.list

import androidx.lifecycle.viewModelScope
import com.eloinavarro.holocron.data.repositories.SWVehicleRepository
import com.eloinavarro.holocron.data.retrofit.SwapiRetrofitDatasource
import com.eloinavarro.holocron.domain.SWVehicle
import com.eloinavarro.holocron.domain.usecase.GetVehiclesUseCase
import com.eloinavarro.holocron.ui.common.list.ListViewModel
import kotlinx.coroutines.launch

class VehicleListViewModel() : ListViewModel<SWVehicle>() {

    init {
        viewModelScope.launch {
            loadNextPage()
        }
    }

    //TODO: Add Hilt and change this
    override val useCase = GetVehiclesUseCase(
        repository = SWVehicleRepository(
            apiDatasource = SwapiRetrofitDatasource()
        )
    )
}