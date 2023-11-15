package com.example.blueridgefinalprojectapp.data

import android.content.Context
import com.example.blueridgefinalprojectapp.model.Contact
import com.example.blueridgefinalprojectapp.model.MenuItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.lang.reflect.Type

fun getJsonDataFromAsset(
    context: Context,
    fileName: String
): String? {
    val jsonString: String
    try {
        jsonString = context.assets.open(fileName).bufferedReader().use {
            it.readText()
        }
    } catch (exp: IOException) {
        exp.printStackTrace()
        return null
    }

    return jsonString
}

fun getDemoBruinMenuItemList(context: Context): MutableList<MenuItem>{
    val jsonFileString = getJsonDataFromAsset(
        context = context, fileName = "demo_bruin_menu.json"
    )
    val type = object : TypeToken<List<MenuItem>>(){}.type
    return Gson().fromJson(jsonFileString, type)
}

fun loadDemoContactList(context: Context)/*: MutableList<Contact>*/ {
    val type = object : TypeToken<List<Contact>>() {}.type
    //return loadListFromJsonFile(context, "demo_contacts.json", type)
    CurrentData.contactList = loadListFromJsonFile(context, "demo_contacts.json", type)
}


fun <T> loadListFromJsonFile(context: Context, fileName: String, typeToken: Type): MutableList<T> {
    val jsonFileString = getJsonDataFromAsset(
        context = context, fileName = fileName
    )
    return Gson().fromJson(jsonFileString, typeToken)
}

object CurrentData {
    var contactList: MutableList<Contact>? = null
}