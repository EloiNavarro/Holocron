package com.eloinavarro.holocron.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessibilityNew
import androidx.compose.material.icons.filled.AirlineSeatReclineExtra
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.RocketLaunch
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.eloinavarro.holocron.R

sealed class NavCommand(
    internal val feature: NavigationFeature,
    internal val subRoute: String = "home",
    private val navArgs: List<NavArg> = emptyList()
) {

    val route = run {
        val arguments = navArgs.map { "{${it.key}}" }
        listOf(feature.route, subRoute)
            .plus(arguments)
            .joinToString("/")
    }

    val args = navArgs.map {
        navArgument(it.key) { type = it.navType }
    }

    class NavContent(feature: NavigationFeature): NavCommand(feature)
    class NavContentDetail(feature: NavigationFeature): NavCommand(feature, "detail", listOf(NavArg.ID)) {
        fun createNavRoute(id: Int) = "${feature.route}/$subRoute/${id}"
    }
}

enum class NavItem(
    val navCommand: NavCommand,
    val icon: ImageVector,
    @StringRes val title: Int
) {
    CHARACTERS(NavCommand.NavContent(NavigationFeature.CHARACTERS), Icons.Default.AccessibilityNew, R.string.title_character),
    PLANETS(NavCommand.NavContent(NavigationFeature.PLANETS), Icons.Default.Public, R.string.title_planet),
    STARSHIPS(NavCommand.NavContent(NavigationFeature.STARSHIPS), Icons.Default.RocketLaunch, R.string.title_starship),
    VEHICLES(NavCommand.NavContent(NavigationFeature.VEHICLES), Icons.Default.AirlineSeatReclineExtra, R.string.title_vehicle)
}

enum class NavArg(val key: String, val navType: NavType<*>) {
    ID("id", NavType.IntType),
}