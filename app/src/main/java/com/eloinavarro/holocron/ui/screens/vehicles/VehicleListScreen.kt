package com.eloinavarro.holocron.ui.screens.vehicles

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.eloinavarro.holocron.domain.SWMovie
import com.eloinavarro.holocron.domain.SWVehicle
import com.eloinavarro.holocron.ui.common.list.SWItemsListScreen

@Composable
fun VehicleListScreen(onItemClick: (SWVehicle) -> Unit) {

    val viewModel: VehicleListViewModel = viewModel()
    val uiState by viewModel.uiStateFlow.collectAsState()

    SWItemsListScreen(
        loading = uiState.loading,
        endReached = uiState.endReached,
        swItems = uiState.items,
        paginator = viewModel,
        onItemClick = onItemClick
    )
}