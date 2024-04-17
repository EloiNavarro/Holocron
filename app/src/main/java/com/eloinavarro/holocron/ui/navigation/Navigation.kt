package com.eloinavarro.holocron.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.eloinavarro.holocron.ui.screens.detail.CharacterDetailScreen
import com.eloinavarro.holocron.ui.screens.detail.PlanetDetailScreen
import com.eloinavarro.holocron.ui.screens.list.CharacterListScreen
import com.eloinavarro.holocron.ui.screens.list.PlanetListScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavigationFeature.PLANETS.route
    ) {
        characters(navController)
        planets(navController)
    }
}

private fun NavGraphBuilder.planets(navController: NavController) {
    navigation(
        startDestination = NavScreen.NavContent(NavigationFeature.PLANETS).route,
        route = NavigationFeature.PLANETS.route
    ) {
        composable(NavScreen.NavContent(NavigationFeature.PLANETS)) {
            PlanetListScreen {
                navController.navigate(NavScreen.NavContentDetail(NavigationFeature.PLANETS).createNavRoute(it.id))
            }
        }
        composable(NavScreen.NavContentDetail(NavigationFeature.PLANETS)) { backStackEntry ->
            val id = backStackEntry.findArg<Int>(NavArg.ID)
            PlanetDetailScreen(
                id = id,
                onUpClick = { navController.popBackStack() }
            )
        }
    }
}

private fun NavGraphBuilder.characters(
    navController: NavController
) {
    navigation(
        startDestination = NavScreen.NavContent(NavigationFeature.CHARACTERS).route,
        route = NavigationFeature.CHARACTERS.route
    ) {
        composable(NavScreen.NavContent(NavigationFeature.CHARACTERS)) {
            CharacterListScreen {
                navController.navigate(NavScreen.NavContentDetail(NavigationFeature.CHARACTERS).createNavRoute(it.id))
            }
        }
        composable(NavScreen.NavContentDetail(NavigationFeature.CHARACTERS)) { backStackEntry ->
            val id = backStackEntry.findArg<Int>(NavArg.ID)
            CharacterDetailScreen(
                id = id,
                onUpClick = { navController.popBackStack() }
            )
        }
    }
}

private fun NavGraphBuilder.composable(
    navItem: NavScreen,
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = navItem.route,
        arguments = navItem.args,
    ) {
        content(it)
    }
}

private inline fun <reified T> NavBackStackEntry.findArg(arg: NavArg): T {
    val value = arguments?.get(arg.key)
    requireNotNull(value) { "Missing value for key ${arg.key}" }
    return value as T
}