package com.eloinavarro.holocron.ui.screens.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.eloinavarro.holocron.ui.common.Screen
import com.eloinavarro.holocron.ui.screens.list.CharacterDetailScaffold


@Composable
fun CharacterDetailScreen(
    id: Int,
    onUpClick: () -> Unit
) {
    val viewModel: DetailViewModel = viewModel(factory = DetailViewModelFactory(id = id))
    val uiState by viewModel.uiStateFlow.collectAsState()
    Screen {
        uiState.character?.let { character ->
            CharacterDetailScaffold(character, onUpClick) { padding ->
                Column(modifier = Modifier.padding(padding)) {
                    CharacterDetail(item = uiState.character)
                }
            }
        }
    }
}