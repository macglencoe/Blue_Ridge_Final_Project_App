package com.example.blueridgefinalprojectapp

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.blueridgefinalprojectapp.components.ContactCard
import com.example.blueridgefinalprojectapp.components.MenuItemCard
import com.example.blueridgefinalprojectapp.components.Toolbar
import com.example.blueridgefinalprojectapp.data.CurrentData
import com.example.blueridgefinalprojectapp.data.loadDemoContactList
import com.example.blueridgefinalprojectapp.model.Contact
import com.example.blueridgefinalprojectapp.model.ToolbarButtonOption
import com.example.compose.AppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.random.Random
import kotlin.random.nextInt

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun contactsPage(
    navController: NavController? = null,
    drawerState: DrawerState? = null,
    drawerScope: CoroutineScope? = null,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    //val contactList = getDemoContactList(context)
    loadDemoContactList(context)
    val contactList = CurrentData.contactList

    if (contactList == null) {
        Text("Contact List Not Loaded Properly")
        return
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Toolbar(
            title = "Campus Contacts",
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
        // Content

        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Adaptive(180.dp),
            verticalItemSpacing = 10.dp,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(10.dp)
        ) {
            items(contactList) {contact ->

                ContactCard(
                    contact,
                    MaterialTheme.colorScheme.primaryContainer,
                    navController,
                    Modifier
                        .clip(shape = RoundedCornerShape(10.dp))
                )
            }
            item {
                var componentHeight by remember { mutableStateOf(0f) }
                val density = LocalDensity.current
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .clip(shape = RoundedCornerShape(10.dp))
                        .onGloballyPositioned {
                            componentHeight = with(density) {
                                it.size.height.toFloat()
                            }
                        }
                        .background(
                            brush = Brush.linearGradient(
                                0f to MaterialTheme.colorScheme.secondaryContainer.copy(0.5f),
                                0.9f to MaterialTheme.colorScheme.tertiaryContainer,
                                start = Offset(0f, componentHeight + 100),
                                end = Offset(0f, 0f)
                            )
                        )


                ) {
                    IconButton(
                        onClick = {
                              var id: Int
                              do {
                                  id = Random.nextInt(999)
                              }
                              while (contactList.any {
                                  it.id == id.toString()
                                  })

                              navController?.navigate("editContact/"+id.toString())
                        },
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        Icon(
                            Icons.Outlined.Add,
                            contentDescription = "Add Contact",
                            tint = MaterialTheme.colorScheme.onTertiaryContainer,
                            modifier = Modifier
                                .size(55.dp)
                        )
                    }
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
private fun ContactsPagePreview() {
    AppTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            contactsPage()
        }
    }
}