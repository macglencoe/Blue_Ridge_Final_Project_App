package com.example.blueridgefinalprojectapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.blueridgefinalprojectapp.components.Toolbar
import com.example.blueridgefinalprojectapp.model.ToolbarButtonOption
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun storeCatalog(
    navController: NavController? = null,
    drawerState: DrawerState? = null,
    drawerScope: CoroutineScope? = null,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Toolbar(
            title = "Bookstore Catalog",
            option1 = ToolbarButtonOption.NAVIGATION,
            option1OnClick = {
                drawerScope?.launch { // Launch scope coroutine *if scope is not null*
                    drawerState?.apply { // Apply *if drawerState is not null*
                        if (isClosed) open() else close() // Toggle drawer
                    }
                }
            },
            option2 = ToolbarButtonOption.NONE,
            option2OnClick = { /* Do Nothing */ })
    }
    //TODO
}