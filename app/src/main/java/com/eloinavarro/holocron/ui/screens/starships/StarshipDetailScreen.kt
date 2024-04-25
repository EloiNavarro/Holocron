package com.eloinavarro.holocron.ui.screens.starships

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.eloinavarro.holocron.ui.common.detail.SWItemDetailScreen


@Composable
fun StarshipDetailScreen(
    id: Int,
    onUpClick: () -> Unit
) {
    val viewModel: StarshipDetailViewModel =
        viewModel(factory = StarshipDetailViewModelFactory(id))
    val uiState by viewModel.uiStateFlow.collectAsState()

    SWItemDetailScreen(uiState.loading, uiState.item, onUpClick)
}