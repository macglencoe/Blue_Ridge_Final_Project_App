package com.example.blueridgefinalprojectapp.components

import android.content.res.Configuration
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.blueridgefinalprojectapp.model.MenuItem
import com.example.compose.AppTheme
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date

@Composable
fun MenuItemCard(
    menuItem: MenuItem
) {
    //TODO

    // Create a card template that each menu item will show up as in the Bruin Menu
    // You can get info about the current menu item from the `menuItem` value

    // The Card should display the title, description, and date of the menu item.
    // The description and date are optional, so make an if statement before displaying to check if they are null
    // For example:
    /*
    if (menuItem.description != null) {
        Text(menuItem.description)
    }
     */
    // You might want to wrap everything inside a `Row()` object so they are grouped together in the catalogue
    // You should also give the Row object a requiredHeight and a fillMaxWidth modifier
    // Like so:
    /*
    Row(
        modifier = Modifier
            .requiredHeight(80.dp)
            .fillMaxWidth()
    ) {
        // Content ...
    }
     */
    // You can use the Preview to see what it looks like while you're developing. Just click "Split" in the top-right.
    // The Preview shows both light and dark themes
    // You can get different font styles and colors from MaterialTheme.typography and MaterialTheme.colorScheme
    // Like so:
    /*
    Text(
        menuItem.title,
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.onSurface
    )
     */
    // Email me if you have any questions lmcdon06@my.blueridgectc.edu

    // Write your code here ...
}


// -- PREVIEW -- //

@RequiresApi(Build.VERSION_CODES.O)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun MenuItemCardPreview() {
    val previewMenuItem: MenuItem = MenuItem(
        id = "0",
        title = "Chicken Caesar Wrap",
        description = "Grilled chicken, crisp romaine lettuce, Parmesan cheese, and Caesar dressing wrapped in a soft tortilla.",
        date = Date.from(
            LocalDate.parse("2023-11-13")
                .atStartOfDay(
                    ZoneId.of("America/Montreal")
                )
                .toInstant()
        )
    )
    AppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            MenuItemCard(menuItem = previewMenuItem)
        }
    }
    
}
