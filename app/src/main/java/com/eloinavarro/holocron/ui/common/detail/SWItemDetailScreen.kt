package com.eloinavarro.holocron.ui.common.detail

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessibilityNew
import androidx.compose.material.icons.filled.AirlineSeatReclineExtra
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.RocketLaunch
import androidx.compose.material.icons.filled.SafetyDivider
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.eloinavarro.holocron.R
import com.eloinavarro.holocron.domain.SWItem
import com.eloinavarro.holocron.domain.SwLink
import com.eloinavarro.holocron.domain.SwLinkType
import com.eloinavarro.holocron.ui.common.ErrorMessage

@Composable
fun <T : SWItem> SWItemDetailScreen(loading: Boolean, result: Result<T>, onUpClick: () -> Unit) {
    if (loading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }

    result
        .onFailure { if (!loading) ErrorMessage(it) }
        .onSuccess { item ->
            SWItemDetailScaffold(item, onUpClick) { padding ->
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(padding)
                ) {
                    item {
                        Header(swItem = item)
                    }
                    item.links.forEach {
                        val (icon, @StringRes stringRes) = it.type.getUiData()
                        section(icon, stringRes, it.links)
                    }
                }
            }
        }
}

@Composable
private fun Header(swItem: SWItem) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(swItem.image)
                .crossfade(true)
                .build(),
            contentScale = ContentScale.Crop,
            contentDescription = "Main image of ${swItem.name}",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = swItem.name,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 0.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}

private fun LazyListScope.section(icon: ImageVector, @StringRes name: Int, items: List<SwLink>) {
    if (items.isEmpty()) {
        return
    }
    item {
        Text(
            text = stringResource(name),
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(16.dp)
        )
    }
    items(items.size) {
        ListItem(
            leadingContent = { Icon(icon, contentDescription = null) },
            headlineContent = { Text(text = items[it].name) }
        )
    }
}

private fun SwLinkType.getUiData(): Pair<ImageVector, Int> = when (this) {
    SwLinkType.CHARACTER -> Icons.Default.AccessibilityNew to R.string.title_character
    SwLinkType.MOVIE -> Icons.Default.Movie to R.string.title_movie
    SwLinkType.SPECIE -> Icons.Default.SafetyDivider to R.string.title_specie
    SwLinkType.STARSHIP -> Icons.Default.RocketLaunch to R.string.title_starship
    SwLinkType.VEHICLE -> Icons.Default.AirlineSeatReclineExtra to R.string.title_vehicle
    SwLinkType.PLANET -> Icons.Default.Public to R.string.title_planet
}