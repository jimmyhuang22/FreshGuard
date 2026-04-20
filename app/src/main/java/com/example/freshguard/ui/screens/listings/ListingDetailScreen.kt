package com.example.freshguard.ui.screens.listings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.freshguard.data.MockData

@Composable
fun ListingDetailScreen(
    listingId: String,
    onBack: () -> Unit
) {
    val listing = MockData.findListingById(listingId)
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (listing == null) {
            Text(
                text = "Listing not found",
                style = MaterialTheme.typography.headlineMedium
            )

            OutlinedButton(
                onClick = onBack
            ) {
                Text("Back")
            }
        } else {
            Text(
                text = listing.title,
                style = MaterialTheme.typography.headlineMedium
            )

            Text(
                text = "Food listing details",
                style = MaterialTheme.typography.bodyLarge
            )

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = "Quantity: ${listing.quantity}",
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Text(
                        text = "Expiry: ${listing.expiryText}",
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Text(
                        text = "Pickup location: ${listing.pickupLocation}",
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Text(
                        text = "Urgency: ${listing.urgency}",
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Text(
                        text = "Notes: ${listing.notes}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

            OutlinedButton(
                onClick = onBack
            ) {
                Text("Back to My Listings")
            }
        }
    }
}