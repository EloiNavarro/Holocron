package com.eloinavarro.holocron.ui.screens.detail

import androidx.compose.foundation.layout.Column
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.eloinavarro.holocron.R
import com.eloinavarro.holocron.ui.common.ArrowBackIcon
import com.eloinavarro.holocron.ui.common.Screen
import com.eloinavarro.holocron.ui.screens.list.SwCharacterItem


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScreen(
    id: String,
    onUpClick:() -> Unit
){
    val viewModel: DetailViewModel = viewModel(factory = DetailViewModelFactory(id = id) )
    val uiState by viewModel.uistate.collectAsState()
    Screen {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = uiState.character?.name ?: "") },
                    navigationIcon = {
                        ArrowBackIcon { onUpClick() }
                    }
                )
            }
        ) { padding ->
            Column(modifier = Modifier.padding(padding)) {
                SwCharacterItem(item = uiState.character, onClick = { })
            }
        }
    }
}