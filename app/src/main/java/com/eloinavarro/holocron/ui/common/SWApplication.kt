package com.eloinavarro.holocron.ui.common

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.eloinavarro.holocron.ui.navigation.NavItem
import com.eloinavarro.holocron.ui.navigation.Navigation

@Composable
fun SWApplication() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: ""

    SWScreen {
        Scaffold(
            bottomBar = {
                NavigationBar {
                    NavItem.values().forEach { item ->
                        val title = stringResource(item.title)
                        NavigationBarItem(
                            selected = currentRoute.contains(item.navCommand.feature.route),
                            onClick = {
                                Log.d("DEBUG", "currentRoute = $currentRoute")
                                Log.d("DEBUG", "feature.route = ${item.navCommand.feature.route}")
                                navController.navigate(item.navCommand.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = title
                                )
                            },
                            label = { Text(text = title) }
                        )
                    }
                }
            }
        ) { padding ->
            Box(modifier = Modifier.padding(padding)) {
                Navigation(navController)
            }
        }
    }
}