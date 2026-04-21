package com.example.freshguard.ui.screens.listings

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.freshguard.data.MockData
import com.example.freshguard.ui.components.FreshGuardTopBar
import com.example.freshguard.ui.components.FormSection
import com.example.freshguard.ui.components.SimpleDropdownField
import com.example.freshguard.ui.theme.FreshGuardTheme
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun AddEditListingScreen(
    listingId: String?,
    onBack: () -> Unit,
    onSaveDraft: () -> Unit,
    onPublish: () -> Unit
) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val existingListing = remember(listingId) {
        listingId?.let { MockData.findListingById(it) }
    }

    val expiryCalendar = remember {
        Calendar.getInstance().apply {
            add(Calendar.DAY_OF_MONTH, 1)
        }
    }
    val pickupCalendar = remember {
        Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 18)
            set(Calendar.MINUTE, 30)
        }
    }

    var itemName by rememberSaveable(listingId) { mutableStateOf(existingListing?.title ?: "Vegetable curry") }
    var category by rememberSaveable(listingId) { mutableStateOf(existingListing?.category ?: "Prepared Meals") }
    var quantity by rememberSaveable(listingId) { mutableStateOf(existingListing?.quantity ?: "4 portions") }
    var urgencyHint by rememberSaveable(listingId) {
        mutableStateOf(existingListing?.let(::editableUrgencyValue) ?: "Donate Soon")
    }
    var storageMethod by rememberSaveable { mutableStateOf("Refrigerated") }
    var expiryDate by rememberSaveable { mutableStateOf(formatDate(expiryCalendar)) }
    var pickupDate by rememberSaveable { mutableStateOf(formatDate(pickupCalendar)) }
    var pickupTime by rememberSaveable { mutableStateOf(formatTime(pickupCalendar)) }
    var pickupLocation by rememberSaveable(listingId) {
        mutableStateOf(existingListing?.pickupLocation ?: "Clayton campus south entrance")
    }
    var notes by rememberSaveable {
        mutableStateOf(existingListing?.notes ?: "Cooked at lunch, cooled quickly, and stored in sealed containers.")
    }
    var foodSafetyConfirmed by rememberSaveable { mutableStateOf(true) }

    val categoryOptions = listOf(
        "Dairy",
        "Fruit",
        "Vegetables",
        "Bakery",
        "Prepared Meals",
        "Snacks",
        "Beverages"
    )
    val urgencyHintOptions = listOf(
        "Urgent",
        "Donate Soon",
        "Safe"
    )
    val storageOptions = listOf(
        "Room temperature",
        "Refrigerated",
        "Frozen",
        "Insulated transport needed"
    )

    val openExpiryDatePicker = {
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                expiryCalendar.set(year, month, dayOfMonth)
                expiryDate = formatDate(expiryCalendar)
            },
            expiryCalendar.get(Calendar.YEAR),
            expiryCalendar.get(Calendar.MONTH),
            expiryCalendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    val openPickupDatePicker = {
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                pickupCalendar.set(year, month, dayOfMonth)
                pickupDate = formatDate(pickupCalendar)
            },
            pickupCalendar.get(Calendar.YEAR),
            pickupCalendar.get(Calendar.MONTH),
            pickupCalendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    val openPickupTimePicker = {
        TimePickerDialog(
            context,
            { _, hourOfDay, minute ->
                pickupCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                pickupCalendar.set(Calendar.MINUTE, minute)
                pickupTime = formatTime(pickupCalendar)
            },
            pickupCalendar.get(Calendar.HOUR_OF_DAY),
            pickupCalendar.get(Calendar.MINUTE),
            false
        ).show()
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            FreshGuardTopBar(
                title = if (existingListing == null) "Add Listing" else "Edit Listing",
                onBackClick = onBack
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(innerPadding)
                .padding(horizontal = 24.dp, vertical = 20.dp)
                .padding(bottom = WindowInsets.ime.asPaddingValues().calculateBottomPadding()),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = if (existingListing == null) {
                        "Create a clear donation listing"
                    } else {
                        "Update an existing donation listing"
                    },
                    style = MaterialTheme.typography.headlineMedium
                )

                Text(
                    text = "Use familiar labels, structured sections, and visible safety details so donors and receivers can understand the listing at a glance.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            FormSection(
                title = "Section A: Item Details",
                description = "Start with the essentials a receiver needs before deciding whether to open the listing, including a first-pass urgency hint."
            ) {
                OutlinedTextField(
                    value = itemName,
                    onValueChange = { itemName = it },
                    label = { Text("Item name") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
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
                    label = { Text("Quantity or portions") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
                )

                SimpleDropdownField(
                    label = "Initial urgency hint",
                    selectedValue = urgencyHint,
                    options = urgencyHintOptions,
                    onValueSelected = { urgencyHint = it }
                )

                Text(
                    text = "FreshGuard will refine this urgency after considering expiry, storage method, pickup timing, and local conditions.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            FormSection(
                title = "Section B: Storage & Safety",
                description = "Highlight storage conditions and confirm the food is still suitable for donation."
            ) {
                SimpleDropdownField(
                    label = "Storage method",
                    selectedValue = storageMethod,
                    options = storageOptions,
                    onValueSelected = { storageMethod = it }
                )

                PickerField(
                    label = "Expiry or use-by date",
                    value = expiryDate,
                    icon = Icons.Default.CalendarMonth,
                    onClick = openExpiryDatePicker
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Top
                ) {
                    Checkbox(
                        checked = foodSafetyConfirmed,
                        onCheckedChange = { foodSafetyConfirmed = it }
                    )

                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(top = 10.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = "Food safety checked",
                            style = MaterialTheme.typography.titleSmall
                        )
                        Text(
                            text = "I have stored this food correctly, checked packaging, and would feel comfortable donating it to another person.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                OutlinedTextField(
                    value = notes,
                    onValueChange = { notes = it },
                    label = { Text("Notes for freshness and handling") },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 3,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Default
                    )
                )
            }

            FormSection(
                title = "Section C: Pickup Details",
                description = "Make collection planning easy with a clear date, time, and location."
            ) {
                PickerField(
                    label = "Pickup date",
                    value = pickupDate,
                    icon = Icons.Default.CalendarMonth,
                    onClick = openPickupDatePicker
                )

                PickerField(
                    label = "Pickup time",
                    value = pickupTime,
                    icon = Icons.Default.Schedule,
                    onClick = openPickupTimePicker
                )

                OutlinedTextField(
                    value = pickupLocation,
                    onValueChange = { pickupLocation = it },
                    label = { Text("Pickup location") },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 2,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedButton(
                    onClick = onSaveDraft,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Save Draft")
                }

                Button(
                    onClick = onPublish,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Publish Listing")
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
private fun PickerField(
    label: String,
    value: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = {},
        modifier = modifier
            .fillMaxWidth()
            .pointerInput(onClick) {
                detectTapGestures(onTap = { onClick() })
            },
        readOnly = true,
        singleLine = true,
        label = { Text(label) },
        trailingIcon = {
            IconButton(onClick = onClick) {
                Icon(
                    imageVector = icon,
                    contentDescription = "Select $label"
                )
            }
        }
    )
}

private fun formatDate(calendar: Calendar): String {
    val formatter = SimpleDateFormat("EEE, d MMM yyyy", Locale.ENGLISH)
    return formatter.format(calendar.time)
}

private fun formatTime(calendar: Calendar): String {
    val formatter = SimpleDateFormat("h:mm a", Locale.ENGLISH)
    return formatter.format(calendar.time)
}

@Preview(showBackground = true)
@Composable
private fun AddEditListingScreenPreview() {
    FreshGuardTheme {
        AddEditListingScreen(
            listingId = null,
            onBack = {},
            onSaveDraft = {},
            onPublish = {}
        )
    }
}

private fun editableUrgencyValue(listing: com.example.freshguard.data.FoodListing): String {
    return when (listing.urgency.lowercase()) {
        "high", "urgent" -> "Urgent"
        "medium", "donate soon" -> "Donate Soon"
        else -> "Safe"
    }
}
