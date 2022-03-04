package com.akole.weddingapp.ui.navigation

import com.akole.weddingapp.ui.screens.HomeScreen

import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.akole.weddingapp.ui.screens.MapScreen
import com.akole.weddingapp.ui.screens.SongsScreen
import com.akole.weddingapp.ui.screens.SplashScreen

@Composable
fun Navigation(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Feature.SPLASH.route
    ) {
        splashNav(navController)
        homeNav(navController)
        mapNav(navController)
        songsNav(navController)
    }
}

private fun NavGraphBuilder.splashNav(navController: NavController) {
    navigation(
        startDestination = NavCommand.ContentType(Feature.SPLASH).route,
        route = Feature.SPLASH.route
    ) {
        composable(NavCommand.ContentType(Feature.SPLASH)) {
            SplashScreen(
                onDelayed = {
                    navController.navigate(NavCommand.ContentType(Feature.HOME).route)
                }
            )
        }
    }
}

private fun NavGraphBuilder.homeNav(navController: NavController) {
    navigation(
        startDestination = NavCommand.ContentType(Feature.HOME).route,
        route = Feature.HOME.route
    ) {
        composable(NavCommand.ContentType(Feature.HOME)) {
            HomeScreen()
        }
    }
}

private fun NavGraphBuilder.mapNav(navController: NavController) {
    navigation(
        startDestination = NavCommand.ContentType(Feature.MAP).route,
        route = Feature.MAP.route
    ) {
        composable(NavCommand.ContentType(Feature.MAP)) {
            MapScreen()
        }
    }
}

private fun NavGraphBuilder.songsNav(navController: NavController) {
    navigation(
        startDestination = NavCommand.ContentType(Feature.SONGS).route,
        route = Feature.SONGS.route
    ) {
        composable(NavCommand.ContentType(Feature.SONGS)) {
            SongsScreen()
        }
    }
}

private fun NavGraphBuilder.composable(
    navCommand: NavCommand,
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = navCommand.route,
        arguments = navCommand.args
    ) {
        content(it)
    }
}
