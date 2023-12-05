package com.example.blueridgefinalprojectapp.components

import android.content.res.Configuration
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.blueridgefinalprojectapp.model.CatalogItem
import com.example.blueridgefinalprojectapp.model.MenuItem
import com.example.compose.AppTheme
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CatalogItemCard(
    catalogItem: CatalogItem,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Column (
        modifier = modifier
    ) {
        Divider(Modifier.padding(5.dp))
        FlowRow (
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            Text(
                catalogItem.title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
            )

            Box (
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .clip(RoundedCornerShape(7.dp))
                    .background(MaterialTheme.colorScheme.primaryContainer)


            ) {
                Text(
                    catalogItem.price.toString(),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(3.dp)

                )
            }
        }
        if (catalogItem.description != null) {
            Box(
                modifier = Modifier
                    .padding(15.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(MaterialTheme.colorScheme.tertiaryContainer)
            ) {
                Text(
                    catalogItem.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier.padding(5.dp)
                )
            }
        }

    }
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
fun CatalogItemCardPreview() {
    val previewCatalogItem: CatalogItem = CatalogItem(
        id = "1",
        title = "Blue Ridge CTC Hoodie",
        description = "Comfortable hoodie featuring the Blue Ridge CTC logo.",
        price = 35.99

    )
    AppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            CatalogItemCard(previewCatalogItem)
        }
    }

}