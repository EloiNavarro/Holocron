package com.eloinavarro.holocron.ui.screens.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Face2
import androidx.compose.material.icons.filled.Height
import androidx.compose.material.icons.filled.PanTool
import androidx.compose.material.icons.filled.Scale
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eloinavarro.holocron.domain.SWCharacterColors
import com.eloinavarro.holocron.domain.SwCharacterAppearance

@Composable
fun SwCharacterDetails(birthDate:String, characterAppearance: SwCharacterAppearance) {
    Column {
        SwBirthdate(birthDate)
        SwHeight(characterAppearance.height)
        SwMass(characterAppearance.weight)
        SwColors(characterAppearance.colors)
    }
}

@Composable
fun SwBirthdate(birthDate: String) {
    SwCharacterDetail(Icons.Filled.CalendarToday, "Born in $birthDate")
}

@Composable
fun SwHeight(height: Float?) {
    height?.let {
        SwCharacterDetail(Icons.Filled.Height, String.format("%.2f m", height))
    }
}

@Composable
fun SwMass(weight: Int?) {
    weight?.let {
        SwCharacterDetail(Icons.Filled.Scale, "$weight Kg")
    }
}

@Composable
fun SwColors(colors: SWCharacterColors) {
    Column {
        SwCharacterDetail(Icons.Filled.PanTool, colors.skin)
        SwCharacterDetail(Icons.Filled.Face2, colors.hair)
        SwCharacterDetail(Icons.Filled.Visibility, colors.eyes)
    }
}

@Composable
private fun SwCharacterDetail(imageVector: ImageVector, detail:String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues = PaddingValues(16.dp, 8.dp)),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = "Icon for character birth date",
            modifier = Modifier
                .size(16.dp)
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = detail,
            fontSize = 18.sp
        )
    }
}