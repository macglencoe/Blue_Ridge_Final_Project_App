package com.example.blueridgefinalprojectapp.model

data class Contact (
    val id: String,
    val firstName: String? = null,
    val lastName: String? = null,
    val title: String,
    val email: String,
    val phoneNumber: String,
)