package com.example.blueridgefinalprojectapp

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFrom
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.blueridgefinalprojectapp.components.AIDisclaimer
import com.example.blueridgefinalprojectapp.components.MenuItemCard
import com.example.blueridgefinalprojectapp.components.Toolbar
import com.example.blueridgefinalprojectapp.data.getDemoBruinMenuItemList
import com.example.blueridgefinalprojectapp.model.ToolbarButtonOption
import com.example.compose.AppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun bruinMenu(
    navController: NavController? = null,
    drawerState: DrawerState? = null,
    drawerScope: CoroutineScope? = null,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val menuItemList = getDemoBruinMenuItemList(context)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Toolbar(
            title = "Bruin Cafe Menu",
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
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.Bottom),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 10.dp)
        ) {
            // Content
            AIDisclaimer()
            LazyColumn {
                items(menuItemList) { menuItem ->
                    MenuItemCard(menuItem = menuItem)
                }
            }
        }
    }
}



// -- PREVIEW -- //

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    widthDp = 360, heightDp = 800,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    widthDp = 360, heightDp = 800,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun BruinMenuPreview() {
    AppTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            bruinMenu()
        }
    }
}