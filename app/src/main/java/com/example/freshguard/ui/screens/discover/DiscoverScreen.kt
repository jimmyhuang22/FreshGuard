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
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.freshguard.data.MockData
import com.example.freshguard.ui.components.FoodListingCard

@Composable
fun DiscoverScreen(
    onOpenDetail: (String) -> Unit
) {
    var selectedFilter by remember { mutableStateOf("All") }

    val filteredListings = when (selectedFilter) {
        "Urgent" -> MockData.discoverListings.filter {
            it.urgency.equals("High", ignoreCase = true)
        }
        "Nearby" -> MockData.discoverListings.filter {
            it.pickupLocation.contains("Clayton", ignoreCase = true) ||
                    it.pickupLocation.contains("campus", ignoreCase = true) ||
                    it.pickupLocation.contains("Notting Hill", ignoreCase = true)
        }
        else -> MockData.discoverListings
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Discover",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Browse nearby food listings and find items that can be rescued in time.",
            style = MaterialTheme.typography.bodyLarge
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            DiscoverFilterChip(
                label = "All",
                selected = selectedFilter == "All",
                onClick = { selectedFilter = "All" }
            )

            DiscoverFilterChip(
                label = "Urgent",
                selected = selectedFilter == "Urgent",
                onClick = { selectedFilter = "Urgent" }
            )

            DiscoverFilterChip(
                label = "Nearby",
                selected = selectedFilter == "Nearby",
                onClick = { selectedFilter = "Nearby" }
            )
        }

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            items(filteredListings) { listing ->
                FoodListingCard(
                    listing = listing,
                    onClick = {
                        onOpenDetail(listing.id)
                    }
                )
            }
        }
    }
}

@Composable
private fun DiscoverFilterChip(
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    FilterChip(
        selected = selected,
        onClick = onClick,
        label = {
            Text(label)
        }
    )
}