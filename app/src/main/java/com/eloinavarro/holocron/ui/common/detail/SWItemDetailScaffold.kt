package com.eloinavarro.holocron.ui.common.detail

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
import com.eloinavarro.holocron.domain.SWItem
import com.eloinavarro.holocron.ui.common.ArrowBackIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SWItemDetailScaffold(
    swItem: SWItem,
    onUpClick: () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    val favoriteState = mutableStateOf(swItem.isFavorite)
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = swItem.name) },
                navigationIcon = { ArrowBackIcon { onUpClick() } },
                actions = { /*CharacterOverflowMenu(swItem.links)*/ }
            )
        },
        floatingActionButton = {
            if (swItem.url.isNotBlank()) {
                FloatingActionButton(onClick = { shareSWItem(context, swItem) }) {
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

fun shareSWItem(context: Context, swItem: SWItem) {
    ShareCompat
        .IntentBuilder(context)
        .setType("text/plain")
        .setSubject(swItem.name)
        .setText(swItem.url)
        .intent
        .also(context::startActivity)
}
