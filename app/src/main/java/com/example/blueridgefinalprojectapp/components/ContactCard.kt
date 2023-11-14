package com.example.blueridgefinalprojectapp.components

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.blueridgefinalprojectapp.model.CatalogItem
import com.example.blueridgefinalprojectapp.model.Contact

@Composable
fun ContactCard(
    contact: Contact
) {
    val context = LocalContext.current
    //TODO
    Column {
        Text(text = contact.title)
        TextButton(
            onClick = {
                context.sendMail(to = contact.email, subject = contact.title)
            }
        ) {
            Column {
                Text("Email")
                Text(
                    contact.email,
                    style = MaterialTheme.typography.bodySmall
                )
            }

        }
        TextButton(
            onClick = {
                context.sendSMS(phone = contact.phoneNumber)
            }
        ) {
            Column {
                Text("SMS")
                Text(
                    contact.phoneNumber,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

// -- Extension Functions -- //

fun Context.sendMail(to: String, subject: String) {
    try {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "vnd.android.cursor.item/email" // or "message/rfc822"
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(to))
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        // TODO: Handle case where no email app is available
    } catch (t: Throwable) {
        // TODO: Handle potential other type of exceptions
    }
}

fun Context.dial(phone: String) {
    try {
        val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null))
        startActivity(intent)
    } catch (t: Throwable) {
        // TODO: Handle potential exceptions
    }
}

fun Context.sendSMS(phone: String) {
    try {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setData(Uri.parse("sms:"))
        intent.putExtra("address", phone)
        startActivity(intent)

    } catch (t: Throwable) {
        // TODO: Handle potential exceptions
    }
}