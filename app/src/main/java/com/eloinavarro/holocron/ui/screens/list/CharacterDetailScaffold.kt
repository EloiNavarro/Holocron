package com.eloinavarro.holocron.ui.screens.list

import android.content.Context
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ShareCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.eloinavarro.holocron.domain.SWCharacter
import com.eloinavarro.holocron.ui.common.ArrowBackIcon
import com.eloinavarro.holocron.ui.common.CharacterOverflowMenu
import com.eloinavarro.holocron.ui.screens.detail.DetailViewModel
import kotlinx.coroutines.flow.update

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScaffold(
    character: SWCharacter,
    onUpClick: () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    val favoriteState = mutableStateOf(character.isFavorite)
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = character.name) },
                navigationIcon = { ArrowBackIcon { onUpClick() } },
                actions = { CharacterOverflowMenu(character.links) }
            )
        },
        floatingActionButton = {
            if (character.url.isNotBlank()) {
                FloatingActionButton(onClick = { shareCharacter(context, character) }) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "Share character"
                    )
                }
            }
        },
        floatingActionButtonPosition = FabPosition.EndOverlay,
        bottomBar = {
            BottomAppBar() {
                IconButton(onClick = { favoriteState.value = !favoriteState.value }) {
                    Icon(
                        imageVector = if (favoriteState.value) {
                            Icons.Default.Favorite
                        } else {
                            Icons.Default.FavoriteBorder
                        },
                        contentDescription = "Favorite"
                    )
                }
            }
        },
        content = content
    )
}

fun shareCharacter(context: Context, character: SWCharacter) {
    ShareCompat
        .IntentBuilder(context)
        .setType("text/plain")
        .setSubject(character.name)
        .setText(character.url)
        .intent
        .also(context::startActivity)
}
