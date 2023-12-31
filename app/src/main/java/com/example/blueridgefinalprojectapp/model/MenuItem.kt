package com.example.blueridgefinalprojectapp.model

import java.time.LocalDate
import java.util.Date

//data class MenuItem(val stringResourceId: Int)

data class MenuItem(
    val id: String,
    val title: String,
    val description: String? = null,
    val date: Date? = null,
    val img: String? = null
)

