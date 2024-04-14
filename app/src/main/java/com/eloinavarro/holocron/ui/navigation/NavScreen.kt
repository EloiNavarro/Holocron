package com.eloinavarro.holocron.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.eloinavarro.holocron.domain.SWCharacter

sealed class NavScreen(
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

    class NavContent(feature: NavigationFeature): NavScreen(feature)
    class NavContentDetail(feature: NavigationFeature): NavScreen(feature, "detail", listOf(NavArg.ID)) {
        fun createNavRoute(id: Int) = "${feature.route}/$subRoute/${id}"
    }
}

enum class NavArg(val key: String, val navType: NavType<*>) {
    ID("id", NavType.IntType),
}