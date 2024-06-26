package com.eloinavarro.holocron.ui.screens.characters

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.eloinavarro.holocron.domain.SWCharacter
import com.eloinavarro.holocron.ui.common.list.SWItemsListScreen

@Composable
fun CharacterListScreen(onItemClick: (SWCharacter) -> Unit) {

    val viewModel: CharacterListViewModel = viewModel()
    val uiState by viewModel.uiStateFlow.collectAsState()

    SWItemsListScreen(
        loading = uiState.loading,
        endReached = uiState.endReached,
        swItems = uiState.items,
        paginator = viewModel,
        onItemClick = onItemClick
    )
}