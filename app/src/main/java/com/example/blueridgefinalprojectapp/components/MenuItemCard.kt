package com.example.blueridgefinalprojectapp.components

import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.blueridgefinalprojectapp.NavigationPage
import com.example.blueridgefinalprojectapp.R
//import com.example.blueridgefinalprojectapp.data.Datasource_Menuitem
import com.example.blueridgefinalprojectapp.model.MenuItem
import com.example.compose.AppTheme
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date
//import com.example.blueridgefinalprojectapp.ui.theme.CustomColorTheme
//import com.example.blueridgefinalprojectapp.ui.theme.md_theme_light_tertiary
import com.example.compose.md_theme_light_primary
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.material3.Divider

@Composable
fun MenuItemCard(
    menuItem: MenuItem,
    //color: Color,
    //navController: NavController? = null,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var componentHeight by remember { mutableStateOf(0f) }
    val density = LocalDensity.current

    Column(
        modifier = modifier
        .onGloballyPositioned {
        componentHeight = with(density) {
            it.size.height.toFloat()
        }
    }
    ) {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                Modifier.weight(0.75f)
            ) {
                Text(
                    menuItem.title,
                    modifier = Modifier.padding(1.dp, 1.dp, 1.dp, 20.dp),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    )

            }
        }
        Divider(
            color = MaterialTheme.colorScheme.primary.copy(alpha=0.2f),
            thickness = 2.dp,
            modifier = Modifier
                .padding(5.dp,5.dp)
        )
        Row(
            Modifier.padding(
                15.dp, 0.dp, 15.dp, 15.dp
            )
        ) {
            if (menuItem.description != null) {
                Text(
                    menuItem.description,
                    modifier = Modifier
                        .weight(0.75f)
                    ,
                    color = md_theme_light_primary
                )
            }
            if (menuItem.img != null) {
                Image(
                    painter = painterResource(
                        context.resources.getIdentifier(
                            menuItem.img,
                            "drawable",
                            context.packageName
                        )
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .weight(0.25f)

                    )

            }

        }
    }

    //TODO

    // Create a card template that each menu item will show up as in the Bruin Menu
    // You can get info about the current menu item from the `menuItem` value

    // The Card should display the title, description, and date of the menu item.
    // The description and date are optional, so make an if statement before displaying to check if they are null
    // For example:

    /*if (menuItem.description != null) {
        Text(
            menuItem.description,
            modifier = Modifier.padding(1.dp, 1.dp, 180.dp, 1.dp),
            color = md_theme_light_primary
            )
        Image(
            painter = painterResource(R.drawable.chicken_caesar_wrap),
            contentDescription = null,
            //modifier = Modifier
                //.size(150.dp),
            modifier = Modifier.padding(240.dp, 1.dp, 1.dp, 1.dp),

        )
    }*/

    // You might want to wrap everything inside a `Row()` object so they are grouped together in the catalogue
    // You should also give the Row object a requiredHeight and a fillMaxWidth modifier
    // Like so:

    /*Row(
        modifier = Modifier
            .requiredHeight(80.dp)
            .fillMaxWidth()
    ) {
        // Content ...
    }*/

    // You can use the Preview to see what it looks like while you're developing. Just click "Split" in the top-right.
    // The Preview shows both light and dark themes
    // You can get different font styles and colors from MaterialTheme.typography and MaterialTheme.colorScheme
    // Like so:

    /*Text(
        menuItem.title,
        modifier = Modifier.padding(1.dp, 1.dp, 1.dp, 20.dp),
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.onSurface,

    )*/

    // Email me if you have any questions lmcdon06@my.blueridgectc.edu

    // Write your code here ...
}

fun Image() {
    TODO("Not yet implemented")
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
