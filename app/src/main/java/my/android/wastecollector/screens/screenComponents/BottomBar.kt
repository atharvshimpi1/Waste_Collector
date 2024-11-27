package my.android.wastecollector.screens.screenComponents

import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import my.android.wastecollector.R
import my.android.wastecollector.navigation.WasteCollectorScreens
import my.android.wastecollector.ui.theme.ButtonColor

data class BottomNavItem(
    val route: String,
    val icon: @Composable (Boolean) -> Unit,
    val label: String
)

@Composable
fun BottomBar(navController: NavController) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val items = listOf(
        BottomNavItem(
            route = WasteCollectorScreens.HomeScreen.name,
            icon = { selected ->
                Icon(
                    if (selected) Icons.Filled.Home else Icons.Outlined.Home,
                    contentDescription = "Home",
                    tint = if (selected) ButtonColor else Color.Unspecified
                )
            },
            label = "Home"
        ),
        BottomNavItem(
            route = WasteCollectorScreens.BookingHistoryScreen.name,
            icon = { selected ->
                Image(
                    painter = painterResource(
                        if (selected) R.drawable.ic_history_colored else R.drawable.ic_history
                    ),
                    contentDescription = "Booking History"
                )
            },
            label = "History"
        ),
        BottomNavItem(
            route = WasteCollectorScreens.ProfileScreen.name,
            icon = { selected ->
                Icon(
                    if (selected) Icons.Filled.Person else Icons.Outlined.Person,
                    contentDescription = "Profile",
                    tint = if (selected) ButtonColor else Color.Unspecified
                )
            },
            label = "Profile"
        )
    )

    NavigationBar(containerColor = Color.White) {
        items.forEach { item ->
            NavigationBarItem(
                icon = { item.icon(currentRoute == item.route) },
                label = { Text(item.label) },
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.id) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarScreenPreview() {
    val navController = rememberNavController()
    BottomBar(navController)
}
