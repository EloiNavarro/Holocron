package com.eloinavarro.holocron.ui.screens.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.eloinavarro.holocron.domain.SWCharacter

@Composable
fun CharacterDetail(item: SWCharacter?) {
    Column{
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(item?.image)
                .crossfade(true)
                .build(),
            contentScale = ContentScale.Crop,
            contentDescription = "Portrait image of ${item?.name}",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(16.dp)
        ){
            Text(
                text = item?.description ?: "",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}