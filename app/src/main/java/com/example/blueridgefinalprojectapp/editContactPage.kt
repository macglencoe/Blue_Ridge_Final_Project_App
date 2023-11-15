package com.example.blueridgefinalprojectapp

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.blueridgefinalprojectapp.components.Toolbar
import com.example.blueridgefinalprojectapp.data.CurrentData
import com.example.blueridgefinalprojectapp.data.loadDemoContactList
import com.example.blueridgefinalprojectapp.model.Contact
import com.example.blueridgefinalprojectapp.model.ToolbarButtonOption
import com.example.compose.AppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun editContactPage(
    contactId: String?,
    navController: NavController? = null,
    drawerState: DrawerState? = null,
    drawerScope: CoroutineScope? = null,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Toolbar(
            title = "Edit Contact",
            option1 = ToolbarButtonOption.NAVIGATION,
            option1OnClick = {
                drawerScope?.launch { // Launch scope coroutine *if scope is not null*
                    drawerState?.apply { // Apply *if drawerState is not null*
                        if (isClosed) open() else close() // Toggle drawer
                    }
                }
            },
            option2 = ToolbarButtonOption.BACK,
            option2OnClick = {
                navController?.popBackStack()
            })
        // Content
        if (contactId != null) {
            Text(contactId)
        } else {
            return
        }

        val contact: Contact = CurrentData.contactList?.firstOrNull { contact ->
            contact.id == contactId
        }?: Contact(contactId)

        if (CurrentData.contactList?.any {it == contact} == false) {
            CurrentData.contactList!!.add(contact)
        }


        if (contact == null) {
            Text(
                "Contact Not Found"
            )
            return
        }

        var firstName by remember { mutableStateOf(contact.firstName) }
        var lastName by remember { mutableStateOf(contact.lastName) }
        var title by remember { mutableStateOf(contact.title) }
        var email by remember { mutableStateOf(contact.email) }
        var phoneNumber by remember { mutableStateOf(contact.phoneNumber) }

        Column(
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {

            OutlinedTextField(
                value = firstName ?: "",
                onValueChange = { firstName = it },
                label = { Text(
                    "First Name",
                    color = MaterialTheme.colorScheme.primary
                ) }
            )
            OutlinedTextField(
                value = lastName ?: "",
                onValueChange = { firstName = it },
                label = { Text(
                    "Last Name",
                    color = MaterialTheme.colorScheme.primary
                ) }
            )
            OutlinedTextField(
                value = title ?: "",
                onValueChange = { title = it },
                label = { Text(
                    "Title",
                    color = MaterialTheme.colorScheme.primary
                ) }
            )
            Divider(Modifier.padding(20.dp))
            OutlinedTextField(
                value = email ?: "",
                onValueChange = {email = it},
                label = {Text(
                    "Email",
                    color = MaterialTheme.colorScheme.tertiary
                )}
            )
            OutlinedTextField(
                value = phoneNumber ?: "",
                onValueChange = {phoneNumber = it},
                label = {Text(
                    "SMS Phone Number",
                    color = MaterialTheme.colorScheme.tertiary
                )}
            )
            Divider(Modifier.padding(20.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(
                        MaterialTheme.colorScheme.primaryContainer,
                        MaterialTheme.colorScheme.onPrimaryContainer

                    )
                ) {
                    Text(
                        "Save Changes",
                        style = MaterialTheme.typography.titleMedium
                        )
                }
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(
                        MaterialTheme.colorScheme.tertiaryContainer,
                        MaterialTheme.colorScheme.onTertiaryContainer
                    )
                ) {
                    Text(
                        "Delete",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }

        }

    }
}

// -- PREVIEW -- //

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    widthDp = 360, heightDp = 800,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    widthDp = 360, heightDp = 800,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun EditContactsPagePreview() {
    AppTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val context = LocalContext.current
            loadDemoContactList(context)
            editContactPage(
                "10"
                )
        }
    }
}