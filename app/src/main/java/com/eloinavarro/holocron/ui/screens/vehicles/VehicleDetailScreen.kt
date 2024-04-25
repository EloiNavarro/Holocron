package com.eloinavarro.holocron.ui.screens.vehicles

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.eloinavarro.holocron.ui.common.detail.SWItemDetailScreen


@Composable
fun VehicleDetailScreen(
    id: Int,
    onUpClick: () -> Unit
) {
    val viewModel: VehicleDetailViewModel =
        viewModel(factory = VehicleDetailViewModelFactory(id))
    val uiState by viewModel.uiStateFlow.collectAsState()

    SWItemDetailScreen(uiState.loading, uiState.item, onUpClick)
}