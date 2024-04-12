package com.eloinavarro.holocron.ui.screens.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.lifecycle.viewmodel.compose.viewModel
import com.eloinavarro.holocron.ui.common.ArrowBackIcon
import com.eloinavarro.holocron.ui.common.CharacterOverflowMenu
import com.eloinavarro.holocron.ui.common.Screen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScreen(
    id: String,
    onUpClick: () -> Unit
) {
    val viewModel: DetailViewModel = viewModel(factory = DetailViewModelFactory(id = id))
    val uiState by viewModel.uiStateFlow.collectAsState()



    Screen {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = uiState.character?.name ?: "") },
                    navigationIcon = { ArrowBackIcon { onUpClick() } },
                    actions = {
                        uiState.character?.let {
                            CharacterOverflowMenu(it.links)
                        }
                    }
                )
            }
        ) { padding ->
            Column(modifier = Modifier.padding(padding)) {
                CharacterDetail(item = uiState.character)
            }
        }
    }
}