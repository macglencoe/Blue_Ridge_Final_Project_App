package com.example.blueridgefinalprojectapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomDrawerValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.rememberBottomDrawerState
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.blueridgefinalprojectapp.ui.theme.BlueRidgeFinalProjectAppTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.rememberDrawerState
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BlueRidgeFinalProjectAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationPage()
                }
            }
        }
    }
}

sealed class Screen (val route: String, @StringRes val resourceId: Int, val icon: ImageVector) {
    object BruinMenu: Screen("bruinMenu", R.string.bruin_menu, Icons.Outlined.List)
    object Catalog: Screen("storeCatalog", R.string.store_catalog, Icons.Outlined.ShoppingCart)
    object Contacts: Screen("contacts", R.string.contacts, Icons.Outlined.ShoppingCart)
}

val items = listOf(
    Screen.BruinMenu,
    Screen.Catalog,
    Screen.Contacts
)

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
private fun NavigationPage(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val startPage = "bruinMenu"

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text("Blue Ridge CTC", modifier = Modifier.padding(16.dp))
                Divider()
                items.forEach { screen ->
                    NavigationDrawerItem(
                        label = { Text(stringResource(screen.resourceId)) },
                        selected = false,
                        onClick = {
                            navController.navigate(screen.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                            scope.launch {// Launch the drawer's scope to close it
                                drawerState.apply {
                                    close()
                                }
                            }
                        }
                    )
                }
            }
            // ...other drawer items
        }
    )
    {
        // Screen Content

        NavHost(navController = navController, startDestination = startPage) {
            composable("bruinMenu") { bruinMenu(
                navController = navController,
                drawerState = drawerState,
                drawerScope = scope
            ) }
            composable("storeCatalog") { storeCatalog(
                navController = navController,
                drawerState = drawerState,
                drawerScope = scope
            ) }
            composable("contacts") { contactsPage(
                navController = navController,
                drawerState = drawerState,
                drawerScope = scope
            ) }

        }
    }





}