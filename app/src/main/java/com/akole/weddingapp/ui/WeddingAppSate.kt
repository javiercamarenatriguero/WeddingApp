package com.akole.weddingapp.ui

import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.akole.weddingapp.ui.navigation.NavItem
import com.akole.weddingapp.ui.navigation.navigatePoppingUpToStartDestination
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberWeddingAppState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
): WeddingAppState = remember(scaffoldState, navController, coroutineScope) {
    WeddingAppState(scaffoldState, navController, coroutineScope)
}

class WeddingAppState(
    val scaffoldState: ScaffoldState,
    val navController: NavHostController,
    private val coroutineScope: CoroutineScope,
) {
    companion object {
        val BOTTOM_NAV_OPTIONS = listOf(NavItem.HOME, NavItem.MAP, NavItem.SONGS, NavItem.PICTURES)
    }

    val currentRoute: String
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination?.route
            ?: ""

    val showUpNavigation: Boolean
        @Composable get() = !NavItem.values().map { it.navCommand.route }.contains(currentRoute)

    fun onUpClick() {
        navController.popBackStack()
    }

    fun onNavItemClick(navItem: NavItem) {
        navController.navigatePoppingUpToStartDestination(navItem.navCommand.route)
    }
}