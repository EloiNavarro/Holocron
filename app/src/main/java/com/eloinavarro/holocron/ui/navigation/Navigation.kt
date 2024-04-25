package com.eloinavarro.holocron.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.eloinavarro.holocron.ui.screens.characters.CharacterDetailScreen
import com.eloinavarro.holocron.ui.screens.planets.PlanetDetailScreen
import com.eloinavarro.holocron.ui.screens.characters.CharacterListScreen
import com.eloinavarro.holocron.ui.screens.planets.PlanetListScreen
import com.eloinavarro.holocron.ui.screens.starships.StarshipDetailScreen
import com.eloinavarro.holocron.ui.screens.starships.StarshipListScreen
import com.eloinavarro.holocron.ui.screens.vehicles.VehicleDetailScreen
import com.eloinavarro.holocron.ui.screens.vehicles.VehicleListScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavigationFeature.CHARACTERS.route
    ) {
        characters(navController)
        planets(navController)
        starships(navController)
        vehicles(navController)
    }
}

private fun NavGraphBuilder.planets(navController: NavController) {
    navigation(
        startDestination = NavCommand.NavContent(NavigationFeature.PLANETS).route,
        route = NavigationFeature.PLANETS.route
    ) {
        composable(NavCommand.NavContent(NavigationFeature.PLANETS)) {
            PlanetListScreen {
                navController.navigate(NavCommand.NavContentDetail(NavigationFeature.PLANETS).createNavRoute(it.id))
            }
        }
        composable(NavCommand.NavContentDetail(NavigationFeature.PLANETS)) { backStackEntry ->
            val id = backStackEntry.findArg<Int>(NavArg.ID)
            PlanetDetailScreen(
                id = id,
                onUpClick = { navController.popBackStack() }
            )
        }
    }
}

private fun NavGraphBuilder.vehicles(navController: NavController) {
    navigation(
        startDestination = NavCommand.NavContent(NavigationFeature.VEHICLES).route,
        route = NavigationFeature.VEHICLES.route
    ) {
        composable(NavCommand.NavContent(NavigationFeature.VEHICLES)) {
            VehicleListScreen {
                navController.navigate(NavCommand.NavContentDetail(NavigationFeature.VEHICLES).createNavRoute(it.id))
            }
        }
        composable(NavCommand.NavContentDetail(NavigationFeature.VEHICLES)) { backStackEntry ->
            val id = backStackEntry.findArg<Int>(NavArg.ID)
            VehicleDetailScreen(
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
        startDestination = NavCommand.NavContent(NavigationFeature.CHARACTERS).route,
        route = NavigationFeature.CHARACTERS.route
    ) {
        composable(NavCommand.NavContent(NavigationFeature.CHARACTERS)) {
            CharacterListScreen {
                navController.navigate(NavCommand.NavContentDetail(NavigationFeature.CHARACTERS).createNavRoute(it.id))
            }
        }
        composable(NavCommand.NavContentDetail(NavigationFeature.CHARACTERS)) { backStackEntry ->
            val id = backStackEntry.findArg<Int>(NavArg.ID)
            CharacterDetailScreen(
                id = id,
                onUpClick = { navController.popBackStack() }
            )
        }
    }
}

private fun NavGraphBuilder.starships(navController: NavController) {
    navigation(
        startDestination = NavCommand.NavContent(NavigationFeature.STARSHIPS).route,
        route = NavigationFeature.STARSHIPS.route
    ) {
        composable(NavCommand.NavContent(NavigationFeature.STARSHIPS)) {
            StarshipListScreen {
                navController.navigate(NavCommand.NavContentDetail(NavigationFeature.STARSHIPS).createNavRoute(it.id))
            }
        }
        composable(NavCommand.NavContentDetail(NavigationFeature.STARSHIPS)) { backStackEntry ->
            val id = backStackEntry.findArg<Int>(NavArg.ID)
            StarshipDetailScreen(
                id = id,
                onUpClick = { navController.popBackStack() }
            )
        }
    }
}

private fun NavGraphBuilder.composable(
    navItem: NavCommand,
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