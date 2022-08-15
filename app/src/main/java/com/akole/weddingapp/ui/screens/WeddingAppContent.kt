package com.akole.weddingapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.akole.weddingapp.ui.WeddingAppState
import com.akole.weddingapp.ui.navigation.AppBottomNavigation
import com.akole.weddingapp.ui.navigation.Navigation
import com.akole.weddingapp.ui.rememberWeddingAppState

@OptIn(androidx.compose.material.ExperimentalMaterialApi::class,
    androidx.compose.foundation.ExperimentalFoundationApi::class
)
@Composable
internal fun WeddingAppContent(appState: WeddingAppState = rememberWeddingAppState()) {
    Scaffold(
        bottomBar = {
            AppBottomNavigation(
                bottomNavOptions = WeddingAppState.BOTTOM_NAV_OPTIONS,
                currentRoute = appState.currentRoute,
                onNavItemClick = { appState.onNavItemClick(it) })
        },
        scaffoldState = appState.scaffoldState
    ) { padding ->
            Box(modifier = androidx.compose.ui.Modifier.padding(padding)) {
                Navigation(appState.navController)
            }
        }
    }