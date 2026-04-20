package com.example.freshguard.ui.screens.discover

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.freshguard.data.FoodListing
import com.example.freshguard.data.MockData
import com.example.freshguard.ui.components.FreshGuardCard
import com.example.freshguard.ui.components.SimpleDropdownField
import com.example.freshguard.ui.components.UrgencyChip
import com.example.freshguard.ui.theme.FreshGuardTheme

@Composable
fun DiscoverScreen(
    onOpenDetail: (String) -> Unit
) {
    var searchQuery by rememberSaveable { mutableStateOf("") }
    var selectedCategory by rememberSaveable { mutableStateOf("All categories") }
    var selectedUrgency by rememberSaveable { mutableStateOf("All urgency") }

    val categoryOptions = listOf("All categories") + MockData.discoverListings
        .map { it.category }
        .distinct()
        .sorted()
    val urgencyOptions = listOf("All urgency", "Urgent", "Donate Soon", "Safe")

    val filteredListings = MockData.discoverListings.filter { listing ->
        val queryMatches = if (searchQuery.isBlank()) {
            true
        } else {
            val query = searchQuery.trim()
            listing.title.contains(query, ignoreCase = true) ||
                listing.category.contains(query, ignoreCase = true) ||
                listing.pickupLocation.contains(query, ignoreCase = true) ||
                listing.contextNote.contains(query, ignoreCase = true)
        }

        val categoryMatches = selectedCategory == "All categories" ||
            listing.category == selectedCategory

        val urgencyMatches = selectedUrgency == "All urgency" ||
            listingUrgencyLabel(listing) == selectedUrgency

        queryMatches && categoryMatches && urgencyMatches
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp, vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Discover",
                style = MaterialTheme.typography.headlineMedium
            )

            Text(
                text = "Search nearby public listings, filter by category and urgency, and compare the context behind each donation window.",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Search food or location") },
                placeholder = { Text("Try yogurt, bakery, Clayton...") },
                singleLine = true,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null
                    )
                }
            )

            SimpleDropdownField(
                label = "Category filter",
                selectedValue = selectedCategory,
                options = categoryOptions,
                onValueSelected = { selectedCategory = it }
            )

            SimpleDropdownField(
                label = "Urgency filter",
                selectedValue = selectedUrgency,
                options = urgencyOptions,
                onValueSelected = { selectedUrgency = it }
            )

            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(bottom = 24.dp)
            ) {
                items(filteredListings) { listing ->
                    DiscoverListingCard(
                        listing = listing,
                        onOpenDetail = {
                            onOpenDetail(listing.id)
                        }
                    )
                }

                if (filteredListings.isEmpty()) {
                    item {
                        FreshGuardCard {
                            Text(
                                text = "No listings match those filters right now.",
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = "Try clearing the search text or choosing a broader category to see more rescue options.",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun DiscoverListingCard(
    listing: FoodListing,
    onOpenDetail: () -> Unit
) {
    FreshGuardCard(
        contentSpacing = 10.dp
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = listing.title,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = listing.category,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            UrgencyChip(urgency = listing.urgency)
        }

        Text(
            text = "Quantity: ${listing.quantity}",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = "Pickup window: ${listing.pickupWindow}",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = "Expiry: ${listing.expiryText}",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = listing.contextNote,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Button(
            onClick = onOpenDetail,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("View details")
        }
    }
}

private fun listingUrgencyLabel(listing: FoodListing): String {
    return when (listing.urgency.lowercase()) {
        "high", "urgent" -> "Urgent"
        "medium", "donate soon" -> "Donate Soon"
        else -> "Safe"
    }
}

@Preview(showBackground = true)
@Composable
private fun DiscoverScreenPreview() {
    FreshGuardTheme {
        DiscoverScreen(onOpenDetail = {})
    }
}
