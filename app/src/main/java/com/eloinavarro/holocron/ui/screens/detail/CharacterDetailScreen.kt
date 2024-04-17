package com.eloinavarro.holocron.ui.screens.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.eloinavarro.holocron.ui.common.detail.SWItemDetailScreen


@Composable
fun CharacterDetailScreen(
    id: Int,
    onUpClick: () -> Unit
) {
    val viewModel: CharacterDetailViewModel =
        viewModel(factory = CharacterDetailViewModelFactory(id))
    val uiState by viewModel.uiStateFlow.collectAsState()

    SWItemDetailScreen(uiState.loading, uiState.item, onUpClick)
}