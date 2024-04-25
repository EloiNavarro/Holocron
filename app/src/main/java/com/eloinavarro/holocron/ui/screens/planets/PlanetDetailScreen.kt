package com.eloinavarro.holocron.ui.screens.planets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.eloinavarro.holocron.ui.common.detail.SWItemDetailScreen


@Composable
fun PlanetDetailScreen(
    id: Int,
    onUpClick: () -> Unit
) {
    val viewModel: PlanetDetailViewModel =
        viewModel(factory = PlanetDetailViewModelFactory(id))
    val uiState by viewModel.uiStateFlow.collectAsState()

    SWItemDetailScreen(uiState.loading, uiState.item, onUpClick)
}