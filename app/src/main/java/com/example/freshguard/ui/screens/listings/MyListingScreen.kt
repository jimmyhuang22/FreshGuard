package com.example.freshguard.ui.screens.listings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.freshguard.data.FoodListing
import com.example.freshguard.data.MockData
import com.example.freshguard.ui.components.FreshGuardCard
import com.example.freshguard.ui.components.FreshGuardTopBar
import com.example.freshguard.ui.components.UrgencyChip
import com.example.freshguard.ui.theme.FreshGuardTheme

@Composable
fun MyListingsScreen(
    onBack: () -> Unit,
    onAddListing: () -> Unit,
    onOpenDetail: (String) -> Unit,
    onEditListing: (String) -> Unit
) {
    var listings by remember { mutableStateOf(MockData.myListings) }
    var donatedIds by remember { mutableStateOf(setOf<String>()) }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            FreshGuardTopBar(
                title = "My Listings",
                onBackClick = onBack
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp, vertical = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Manage your active donation posts, update details when pickup plans change, and mark items as completed once they are rescued.",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Button(
                onClick = onAddListing,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Add New Listing")
            }

            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(bottom = 24.dp)
            ) {
                items(listings, key = { it.id }) { listing ->
                    MyListingManagementCard(
                        listing = listing,
                        donated = donatedIds.contains(listing.id),
                        onOpenDetail = {
                            onOpenDetail(listing.id)
                        },
                        onEdit = {
                            onEditListing(listing.id)
                        },
                        onDelete = {
                            listings = listings.filterNot { it.id == listing.id }
                            donatedIds = donatedIds - listing.id
                        },
                        onMarkDonated = {
                            donatedIds = donatedIds + listing.id
                        }
                    )
                }

                if (listings.isEmpty()) {
                    item {
                        FreshGuardCard {
                            Text(
                                text = "No listings left to manage.",
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = "Create a new listing to keep your dashboard active and show how donation actions would appear in the prototype.",
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
private fun MyListingManagementCard(
    listing: FoodListing,
    donated: Boolean,
    onOpenDetail: () -> Unit,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    onMarkDonated: () -> Unit
) {
    val statusText = if (donated) "Donated" else "Active"

    FreshGuardCard(
        containerColor = if (donated) {
            MaterialTheme.colorScheme.secondaryContainer
        } else {
            MaterialTheme.colorScheme.surface
        },
        contentColor = if (donated) {
            MaterialTheme.colorScheme.onSecondaryContainer
        } else {
            MaterialTheme.colorScheme.onSurface
        },
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
            text = "Status: $statusText",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = listing.contextNote,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            OutlinedButton(
                onClick = onEdit,
                modifier = Modifier.weight(1f)
            ) {
                Text("Edit")
            }

            Button(
                onClick = onOpenDetail,
                modifier = Modifier.weight(1f)
            ) {
                Text("Open Detail")
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            OutlinedButton(
                onClick = onDelete,
                modifier = Modifier.weight(1f)
            ) {
                Text("Delete")
            }

            Button(
                onClick = onMarkDonated,
                modifier = Modifier.weight(1f),
                enabled = !donated
            ) {
                Text(if (donated) "Donated" else "Mark Donated")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MyListingsScreenPreview() {
    FreshGuardTheme {
        MyListingsScreen(
            onBack = {},
            onAddListing = {},
            onOpenDetail = {},
            onEditListing = {}
        )
    }
}
