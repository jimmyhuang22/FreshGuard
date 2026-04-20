package com.example.freshguard.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.freshguard.data.FoodListing

@Composable
fun FoodListingCard(
    listing: FoodListing,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = listing.title,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = "Quantity: ${listing.quantity}",
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = "Expiry: ${listing.expiryText}",
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = "Pickup: ${listing.pickupLocation}",
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = "Urgency: ${listing.urgency}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}