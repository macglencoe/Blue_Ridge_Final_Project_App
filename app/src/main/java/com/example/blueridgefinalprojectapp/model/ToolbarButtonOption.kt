package com.example.blueridgefinalprojectapp.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import com.example.blueridgefinalprojectapp.R

enum class ToolbarButtonOption(
    val contentDescription: String,
    val iconResource: Int?
) {
    NAVIGATION(
        "Navigation Menu", R.drawable.menu_24px
    ),
    BACK(
        "Go Back", R.drawable.arrow_back_24px
    ),
    NONE(
        "", null
    )

}