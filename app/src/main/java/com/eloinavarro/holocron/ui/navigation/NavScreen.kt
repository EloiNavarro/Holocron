package com.eloinavarro.holocron.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.eloinavarro.holocron.domain.SWCharacter

sealed class NavScreen(val baseRoute: String, private val navArgs: List<NavArg> = emptyList()) {

    val route = run {
        val arguments = navArgs.map { "{${it.key}}" }
        listOf(baseRoute).plus(arguments).joinToString("/")
    }

    val args = navArgs.map {
        navArgument(it.key) { type = it.navType }
    }

    object CharacterListScreen : NavScreen("character_list")
    object CharacterDetailScreen : NavScreen("character_detail", listOf(NavArg.ID)) {
        fun createNavRoute(character: SWCharacter) = "$baseRoute/${character.id}"
    }
}

enum class NavArg(val key: String, val navType: NavType<*>) {
    ID("id", NavType.StringType),
}