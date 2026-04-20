package com.example.freshguard.ui.screens.listings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.freshguard.data.MockData
import com.example.freshguard.ui.components.FoodListingCard

@Composable
fun MyListingsScreen(
    onBack: () -> Unit,
    onAddListing: () -> Unit,
    onOpenDetail: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "My Listings",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Manage the food items you have shared.",
            style = MaterialTheme.typography.bodyLarge
        )

        Button(
            onClick = onAddListing,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add New Listing")
        }

        OutlinedButton(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Back to Home")
        }

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(top = 8.dp, bottom = 24.dp)
        ) {
            items(MockData.myListings) { listing ->
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