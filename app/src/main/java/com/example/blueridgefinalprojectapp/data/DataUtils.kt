package com.example.blueridgefinalprojectapp.data

import android.content.Context
import com.example.blueridgefinalprojectapp.model.CatalogItem
import com.example.blueridgefinalprojectapp.model.Contact
import com.example.blueridgefinalprojectapp.model.MenuItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedWriter
import java.io.File
import java.io.FileNotFoundException
import java.io.FileWriter
import java.io.IOException
import java.io.Writer
import java.lang.reflect.Type

// -- Asset Loading -- //
private fun getJsonDataFromAsset(
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

private fun <T> loadListFromAsset(context: Context,fileName: String,typeToken: Type): MutableList<T> {
    val jsonFileString = getJsonDataFromAsset(context, fileName)
    return Gson().fromJson(jsonFileString, typeToken)
}


// -- Internal Data Loading -- //
private fun <T> loadListFromJsonFile(context: Context, fileName: String, typeToken: Type): MutableList<T>? {
    val file = File(context.filesDir, fileName)
    try {
        file.readText()
    } catch (e: FileNotFoundException) {
        return null
    }
    val jsonFileString = file.readText()
    return Gson().fromJson(jsonFileString, typeToken)
}


// -- Internal Data Saving -- //
private inline fun <reified T> saveListToJsonFile(context: Context, fileName: String, list: MutableList<T>) {
    val jsonFileString = Gson().toJson(list.toTypedArray())
    var output: Writer?
    val file = File(context.filesDir, fileName)
    if (!file.exists()) {
        file.createNewFile()
    }
    output = BufferedWriter(FileWriter(file))
    output.write(jsonFileString)
    output.close()
}


// -- Exposed Functions -- //
fun loadDemoContactList(context: Context) {
    val type = object : TypeToken<List<Contact>>() {}.type
    CurrentData.demoContactList = loadListFromAsset(context, "demo_contacts.json", type)
}
fun getDemoBruinMenuItemList(context: Context): MutableList<MenuItem>{
    val jsonFileString = getJsonDataFromAsset(
        context = context, fileName = "demo_bruin_menu.json"
    )
    val type = object : TypeToken<List<MenuItem>>(){}.type
    return Gson().fromJson(jsonFileString, type)
}
fun getDemoBookstoreCatalogItemList(context: Context): MutableList<CatalogItem> {
    val jsonFileString = getJsonDataFromAsset(
        context = context, fileName = "demo_bookstore_catalog.json"
    )
    val type = object : TypeToken<List<CatalogItem>>(){}.type
    return Gson().fromJson(jsonFileString, type)
}
fun loadContactList(context: Context) {
    val type = object : TypeToken<List<Contact>>() {}.type
    CurrentData.contactList = loadListFromJsonFile(context, "contacts.json", type)
        ?: mutableListOf()
}
fun saveContacts(context: Context) {
    if (CurrentData.contactList != null)
        saveListToJsonFile(
            context = context,
            fileName = "contacts.json",
            list = CurrentData.contactList!!
        )
}


// -- Global Data -- //
object CurrentData {
    var contactList: MutableList<Contact>? = null
    var demoContactList: MutableList<Contact>? = null
}