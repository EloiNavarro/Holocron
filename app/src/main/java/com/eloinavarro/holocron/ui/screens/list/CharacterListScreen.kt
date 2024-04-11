package com.eloinavarro.holocron.ui.screens.list

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.eloinavarro.holocron.R
import com.eloinavarro.holocron.domain.SWCharacter
import com.eloinavarro.holocron.ui.common.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterListScreen(
    viewModel: ListViewModel = viewModel(),
    onItemClick: (SWCharacter) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    Screen {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = stringResource(R.string.app_name)) },
                    navigationIcon = {}
                )
            }
        ) { padding ->
            LazyVerticalGrid(
                columns = GridCells.Adaptive(180.dp),
                contentPadding = padding
            ) {
                items(uiState.characters.size) { i ->
                    if (i >= uiState.characters.size - 1
                        && !uiState.endReached
                        && !uiState.loading
                    ) {
                        viewModel.loadNextPage()
                    }
                    SwCharacterItem(
                        item = uiState.characters[i],
                        onClick = { onItemClick(uiState.characters[i]) })
                }
                item (span = { GridItemSpan(2) }) {
                    if (uiState.loading) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
        }
    }
}