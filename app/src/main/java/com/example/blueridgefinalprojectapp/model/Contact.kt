package com.example.blueridgefinalprojectapp.model

data class Contact (
    val id: String,
    var firstName: String? = null,
    var lastName: String? = null,
    var title: String,
    var email: String,
    var phoneNumber: String,
)