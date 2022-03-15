package com.akole.weddingapp.ui.navigation

import com.akole.weddingapp.ui.screens.home.HomeScreen

import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.akole.weddingapp.ui.screens.map.MapScreen
import com.akole.weddingapp.ui.screens.pictures.PicturesScreen
import com.akole.weddingapp.ui.screens.songs.SongsScreen

@Composable
fun Navigation(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Feature.HOME.route
    ) {
        homeNav(navController)
        mapNav(navController)
        songsNav(navController)
        picturesNav(navController)
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

private fun NavGraphBuilder.picturesNav(navController: NavController) {
    navigation(
        startDestination = NavCommand.ContentType(Feature.PICTURES).route,
        route = Feature.PICTURES.route
    ) {
        composable(NavCommand.ContentType(Feature.PICTURES)) {
            PicturesScreen()
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
