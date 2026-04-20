package com.example.freshguard.ui.screens.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.freshguard.ui.components.ProfileOptionRow

@Composable
fun ProfileScreen() {
    val scrollState = rememberScrollState()

    var notificationsEnabled by remember { mutableStateOf(true) }
    var darkModeEnabled by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Profile",
            style = MaterialTheme.typography.headlineMedium
        )

        Card {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Card(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "JH",
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "Jihao Huang",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = "jhua0215@student.monash.edu",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "FreshGuard community member",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }

        Card {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Preferences",
                    style = MaterialTheme.typography.titleMedium
                )

                ProfileOptionRow(
                    title = "Notifications",
                    subtitle = "Get reminders for urgent food items and pickup timing",
                    trailingContent = {
                        Switch(
                            checked = notificationsEnabled,
                            onCheckedChange = { notificationsEnabled = it }
                        )
                    }
                )

                HorizontalDivider()

                ProfileOptionRow(
                    title = "Dark mode",
                    subtitle = "Prototype setting for appearance preference",
                    trailingContent = {
                        Switch(
                            checked = darkModeEnabled,
                            onCheckedChange = { darkModeEnabled = it }
                        )
                    }
                )

                HorizontalDivider()

                ProfileOptionRow(
                    title = "Preferred pickup radius",
                    subtitle = "Currently set to within 5 km",
                    trailingContent = {
                        Text(
                            text = "5 km",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                )

                HorizontalDivider()

                ProfileOptionRow(
                    title = "Dietary interests",
                    subtitle = "Bakery, Dairy, Prepared Meals",
                    trailingContent = {
                        Text(
                            text = "Edit",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                )
            }
        }

        Card {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "Account actions",
                    style = MaterialTheme.typography.titleMedium
                )

                OutlinedButton(
                    onClick = { },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Manage saved locations")
                }

                OutlinedButton(
                    onClick = { },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Privacy and safety settings")
                }

                OutlinedButton(
                    onClick = { },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Log out")
                }
            }
        }
    }
}