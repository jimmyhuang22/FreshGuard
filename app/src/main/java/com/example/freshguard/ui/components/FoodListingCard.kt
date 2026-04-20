package com.example.freshguard.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
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
    FreshGuardCard(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
        contentSpacing = 8.dp
    ) {
        Row {
            Text(
                text = listing.title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(12.dp))

            UrgencyChip(urgency = listing.urgency)
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
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
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = listing.notes,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
