package com.eloinavarro.holocron.ui.screens.list

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.eloinavarro.holocron.R
import com.eloinavarro.holocron.ui.common.ArrowBackIcon
import com.eloinavarro.holocron.ui.common.Screen
import androidx.lifecycle.viewmodel.compose.viewModel
import com.eloinavarro.holocron.domain.SWCharacter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterListScreen(
    viewModel: ListViewModel = viewModel(),
    onItemClick:(SWCharacter) -> Unit
) {
    val uiState by viewModel.uistate.collectAsState()
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
                    SwCharacterItem(item = item, onClick = { onItemClick(item) })
                }
            }
        }
    }
}