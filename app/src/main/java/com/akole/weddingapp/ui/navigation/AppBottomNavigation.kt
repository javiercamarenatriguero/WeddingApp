package com.akole.weddingapp.ui.navigation

import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource

@Composable
fun AppBottomNavigation(
    bottomNavOptions: List<NavItem>,
    currentRoute: String,
    onNavItemClick: (NavItem) -> Unit
) {
    WeddingAppBottomNavigation {
        bottomNavOptions.forEach { navItem ->
            val title = navItem.title
            BottomNavigationItem(
                selected = currentRoute.contains(navItem.navCommand.feature.route),
                onClick = { onNavItemClick(navItem) },
                icon = {
                    Icon(
                        imageVector = navItem.icon,
                        contentDescription = stringResource(id = title)
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = title),
                        color = Color.White
                    )
                }
            )
        }
    }
}