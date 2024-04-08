package com.eloinavarro.holocron.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.eloinavarro.holocron.ui.screens.detail.CharacterDetailScreen
import com.eloinavarro.holocron.ui.screens.list.CharacterListScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavScreen.CharacterListScreen.route
    ) {
        composable(NavScreen.CharacterListScreen) {
            CharacterListScreen {
                navController.navigate(NavScreen.CharacterDetailScreen.createNavRoute(it))
            }
        }
        composable(NavScreen.CharacterDetailScreen) { backStackEntry ->
            val id = backStackEntry.findArg<String>(NavArg.ID)
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