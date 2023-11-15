package com.example.blueridgefinalprojectapp.components

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.blueridgefinalprojectapp.model.CatalogItem
import com.example.blueridgefinalprojectapp.model.Contact

@Composable
fun ContactCard(
    contact: Contact,
    color: Color,
    navController: NavController? = null,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var componentHeight by remember { mutableStateOf(0f) }
    val density = LocalDensity.current


    Column(
        modifier = modifier
            .onGloballyPositioned {
                componentHeight = with(density) {
                    it.size.height.toFloat()
                }
            }
            .background(
                brush = Brush.linearGradient(
                    0f to MaterialTheme.colorScheme.secondaryContainer.copy(0.5f),
                    0.9f to color,
                    start = Offset(0f, componentHeight + 100),
                    end = Offset(0f, 0f)
                )
            )
    ) {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                Modifier.weight(0.75f)
            ) {
                Text(
                    text = contact.title?: "No Title",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.padding(
                        15.dp,15.dp,0.dp,0.dp
                    )
                )
            }
            Box(
                Modifier.weight(0.25f)
            ) {
                IconButton(
                    onClick = { navController?.navigate("editContact/" + contact.id) },
                ) {
                    Icon(
                        Icons.Outlined.Build,
                        "Edit Contact",
                        tint = MaterialTheme.colorScheme.secondary.copy(alpha=0.2f)
                    )
                }
            }
        }
        Divider(
            color = MaterialTheme.colorScheme.primary.copy(alpha=0.2f),
            thickness = 2.dp,
            modifier = Modifier
                .padding(5.dp,5.dp)
        )
        Column(
            Modifier.padding(
                15.dp, 0.dp, 15.dp, 15.dp
            )
        ) {
            if (contact.firstName != null && contact.lastName != null) {
                Text(
                    "${contact.firstName} ${contact.lastName}",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.tertiary
                )
            }
            if (contact.email != null) {
                TextButton(
                    onClick = {
                        context.sendMail(to = contact.email!!)
                    }
                ) {
                    Column {
                        Text("Email")
                        Text(
                            contact.email!!,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
            if (contact.phoneNumber != null) {
                TextButton(
                    onClick = {
                        context.sendSMS(phone = contact.phoneNumber!!)
                    }
                ) {
                    Column {
                        Text("SMS")
                        Text(
                            contact.phoneNumber!!,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }

    }
}

// -- Extension Functions -- //

fun Context.sendMail(to: String) {
    try {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "vnd.android.cursor.item/email" // or "message/rfc822"
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(to))
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