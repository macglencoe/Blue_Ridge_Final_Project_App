package com.example.blueridgefinalprojectapp.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.blueridgefinalprojectapp.model.ToolbarButtonOption

@Composable
fun ToolbarButton(
    option: ToolbarButtonOption,
    onClick: () -> Unit
) {
    IconButton(
        onClick = { onClick() } // Call function specified in arguments
    ) {
        // Only create an Icon if the Option has a specified iconResource
        if (option.iconResource != null) {
            Icon(
                painter = painterResource(id = option.iconResource),
                contentDescription = option.contentDescription,
                modifier = Modifier.size(48.dp)
            )
        }
    }

}