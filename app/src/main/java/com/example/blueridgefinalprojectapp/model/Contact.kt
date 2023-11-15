package com.example.blueridgefinalprojectapp.model

data class Contact (
    val id: String,
    var firstName: String? = null,
    var lastName: String? = null,
    var title: String? = null,
    var email: String? = null,
    var phoneNumber: String? = null,
    var constant: Boolean = false
) {

}