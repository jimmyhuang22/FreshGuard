package com.example.freshguard.ui.screens.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.freshguard.ui.components.FreshGuardCard
import com.example.freshguard.ui.components.ProfileOptionRow
import com.example.freshguard.ui.components.SimpleDropdownField
import com.example.freshguard.ui.theme.FreshGuardTheme

@Composable
fun ProfileScreen() {
    var dietaryPreference by rememberSaveable { mutableStateOf("Vegetarian-friendly") }
    var pickupRadius by rememberSaveable { mutableFloatStateOf(5f) }
    var urgentReminders by rememberSaveable { mutableStateOf(true) }
    var personalisedSuggestions by rememberSaveable { mutableStateOf(true) }

    val dietaryOptions = listOf(
        "Vegetarian-friendly",
        "No preference",
        "Vegan-friendly",
        "Halal-friendly",
        "Dairy-free"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Profile",
                style = MaterialTheme.typography.headlineMedium
            )

            Text(
                text = "Adjust account preferences so FreshGuard can prioritise nearby, relevant, and time-sensitive donation opportunities.",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        FreshGuardCard {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Surface(
                    modifier = Modifier.size(72.dp)
                        .clip(CircleShape),
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.secondaryContainer
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "AC",
                            style = MaterialTheme.typography.headlineSmall,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "Alex Chen",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = "alex.chen@student.monash.edu",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "FreshGuard community donor and receiver",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        FreshGuardCard {
            Text(
                text = "Preferences",
                style = MaterialTheme.typography.titleLarge
            )

            SimpleDropdownField(
                label = "Dietary preference",
                selectedValue = dietaryPreference,
                options = dietaryOptions,
                onValueSelected = { dietaryPreference = it }
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = "Pickup radius",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "${pickupRadius.toInt()} km from your preferred location",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Slider(
                    value = pickupRadius,
                    onValueChange = { pickupRadius = it },
                    valueRange = 1f..15f,
                    steps = 13
                )
            }

            ProfileOptionRow(
                title = "Urgent reminders",
                subtitle = "Alert me when a saved or nearby listing is close to expiry.",
                trailingContent = {
                    Switch(
                        checked = urgentReminders,
                        onCheckedChange = { urgentReminders = it }
                    )
                }
            )

            ProfileOptionRow(
                title = "Personalised suggestions",
                subtitle = "Use my dietary preference and recent activity to surface more relevant listings.",
                trailingContent = {
                    Switch(
                        checked = personalisedSuggestions,
                        onCheckedChange = { personalisedSuggestions = it }
                    )
                }
            )
        }

        FreshGuardCard {
            Text(
                text = "Account details",
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = "Preferred pickup suburb: Clayton",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Saved locations: Campus south entrance, Monash area library",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Prototype mode: Mock preferences only, ready for Firebase/Room integration later",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Button(
            onClick = { },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save changes")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileScreenPreview() {
    FreshGuardTheme {
        ProfileScreen()
    }
}
