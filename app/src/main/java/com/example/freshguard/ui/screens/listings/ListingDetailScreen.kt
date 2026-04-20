package com.example.freshguard.ui.screens.listings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import com.example.freshguard.ui.components.ContextAlertCard
import com.example.freshguard.ui.components.FreshGuardCard
import com.example.freshguard.ui.components.FreshGuardTopBar
import com.example.freshguard.ui.components.UrgencyChip
import com.example.freshguard.ui.theme.FreshGuardTheme

@Composable
fun ListingDetailScreen(
    listingId: String,
    onBack: () -> Unit,
    onOpenChat: (String) -> Unit
) {
    val listing = MockData.findListingById(listingId)
    var saved by rememberSaveable(listingId) { mutableStateOf(false) }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            FreshGuardTopBar(
                title = "Listing Details",
                onBackClick = onBack
            )
        }
    ) { innerPadding ->
        if (listing == null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Listing not found",
                    style = MaterialTheme.typography.headlineMedium
                )
                OutlinedButton(onClick = onBack) {
                    Text("Back")
                }
            }
        } else {
            val freshnessProgress = freshnessProgressFor(listing)
            val freshnessPercent = (freshnessProgress * 100).toInt()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(innerPadding)
                    .padding(horizontal = 24.dp, vertical = 20.dp),
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Top
                ) {
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(
                            text = listing.title,
                            style = MaterialTheme.typography.headlineMedium
                        )
                        Text(
                            text = "${listing.category} • Shared by ${listing.donorName}",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    UrgencyChip(urgency = listing.urgency)
                }

                FreshGuardCard(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                ) {
                    Text(
                        text = "Freshness and urgency",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = "$freshnessPercent% of the safe rescue window remains. This listing should be prioritised according to its category, expiry timing, and pickup constraints.",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    LinearProgressIndicator(
                        progress = { freshnessProgress },
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                ContextAlertCard(
                    title = "Why this listing is time-sensitive",
                    message = listing.contextNote
                )

                FreshGuardCard {
                    Text(
                        text = "Pickup details",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = "Pickup window: ${listing.pickupWindow}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "Pickup location: ${listing.pickupLocation}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "Quantity: ${listing.quantity}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "Expiry: ${listing.expiryText}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                FreshGuardCard {
                    Text(
                        text = "Donor notes",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = listing.notes,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedButton(
                        onClick = { saved = !saved },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(if (saved) "Saved" else "Save Listing")
                    }

                    Button(
                        onClick = { onOpenChat(listing.id) },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Message Donor")
                    }
                }
            }
        }
    }
}

private fun freshnessProgressFor(listing: FoodListing): Float {
    return when (listing.urgency.lowercase()) {
        "high", "urgent" -> 0.28f
        "medium", "donate soon" -> 0.56f
        else -> 0.82f
    }
}

@Preview(showBackground = true)
@Composable
private fun ListingDetailScreenPreview() {
    FreshGuardTheme {
        ListingDetailScreen(
            listingId = "milk_001",
            onBack = {},
            onOpenChat = {}
        )
    }
}
