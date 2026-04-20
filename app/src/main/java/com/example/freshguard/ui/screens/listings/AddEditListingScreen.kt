package com.example.freshguard.ui.screens.listings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.freshguard.ui.components.FormSection
import com.example.freshguard.ui.components.SimpleDropdownField

@Composable
fun AddEditListingScreen(
    onBack: () -> Unit,
    onSave: () -> Unit
) {
    val scrollState = rememberScrollState()

    var foodName by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("Dairy") }
    var quantity by remember { mutableStateOf("") }
    var expiryDate by remember { mutableStateOf("") }
    var expiryTime by remember { mutableStateOf("") }
    var pickupLocation by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }

    val categoryOptions = listOf(
        "Dairy",
        "Fruit",
        "Vegetables",
        "Bakery",
        "Prepared Meals",
        "Snacks",
        "Beverages"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = "Add / Edit Listing",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Create a food listing using a simple, structured form.",
            style = MaterialTheme.typography.bodyLarge
        )

        FormSection {
            OutlinedTextField(
                value = foodName,
                onValueChange = { foodName = it },
                label = { Text("Food name") },
                modifier = Modifier.fillMaxWidth()
            )

            SimpleDropdownField(
                label = "Category",
                selectedValue = category,
                options = categoryOptions,
                onValueSelected = { category = it }
            )

            OutlinedTextField(
                value = quantity,
                onValueChange = { quantity = it },
                label = { Text("Quantity") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        FormSection {
            OutlinedTextField(
                value = expiryDate,
                onValueChange = { expiryDate = it },
                label = { Text("Expiry date (e.g. 20 Apr 2026)") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = expiryTime,
                onValueChange = { expiryTime = it },
                label = { Text("Expiry time (e.g. 8:00 PM)") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = pickupLocation,
                onValueChange = { pickupLocation = it },
                label = { Text("Pickup suburb / location") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        FormSection {
            OutlinedTextField(
                value = notes,
                onValueChange = { notes = it },
                label = { Text("Notes") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier
                    .fillMaxWidth()
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                onClick = onSave,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save Listing")
            }

            OutlinedButton(
                onClick = onBack,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Cancel")
            }
        }
    }
}