package com.example.blueridgefinalprojectapp.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blueridgefinalprojectapp.model.ToolbarButtonOption

@Composable
fun Toolbar(
    title: String,
    option1: ToolbarButtonOption, option1OnClick: () -> Unit,
    option2: ToolbarButtonOption, option2OnClick: () -> Unit,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .requiredHeight(60.dp)
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Top,
        modifier = modifier
    ) {
        ToolbarButton(
            option = option1,
            onClick = option1OnClick
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(
                10.dp,
                Alignment.CenterHorizontally
            ),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .weight(weight = 1f)
        ) {
            Text(
                text = title, // Set Title text
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
        ToolbarButton(
            option = option2,
            onClick = option2OnClick
        )
    }
}