package com.eloinavarro.holocron.ui.screens.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.eloinavarro.holocron.ui.common.ArrowBackIcon
import com.eloinavarro.holocron.ui.common.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterListScreen(
    viewModel: ListViewModel = viewModel(),
    onItemClick:(SWCharacter) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    Screen {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = stringResource(R.string.app_name)) },
                    navigationIcon = {
                        ArrowBackIcon {}
                    }
                )
            }
        ) { padding ->
            LazyColumn(modifier = Modifier.padding(padding)) {
                items(uiState.characters) { item ->
                    if(uiState.characters.last() == item
                        && !uiState.endReached
                        && !uiState.loading) {
                        viewModel.loadNextPage()
                    }
                    SwCharacterItem(item = item, onClick = { onItemClick(item) })
                }
                item {
                    if(uiState.loading) {
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(16.dp),
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