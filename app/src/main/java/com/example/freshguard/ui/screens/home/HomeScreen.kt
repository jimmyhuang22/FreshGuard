package com.example.freshguard.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material3.Button
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.freshguard.data.FoodListing
import com.example.freshguard.data.MockData
import com.example.freshguard.ui.components.ContextAlertCard
import com.example.freshguard.ui.components.FreshGuardCard
import com.example.freshguard.ui.components.HomeQuickActionCard
import com.example.freshguard.ui.components.UrgencyChip
import com.example.freshguard.ui.theme.FreshGuardTheme

@Composable
fun HomeScreen(
    onOpenDiscover: () -> Unit,
    onOpenMyListings: () -> Unit,
    onOpenAddListing: () -> Unit,
    onOpenListingDetail: (String) -> Unit
) {
    val urgentListings = MockData.myListings.filter { listing ->
        listing.urgency.equals("High", ignoreCase = true) ||
            listing.expiryText.contains("today", ignoreCase = true) ||
            listing.expiryText.contains("tonight", ignoreCase = true)
    }.take(3)

    val urgentCount = urgentListings.size
    val monitoredItems = MockData.myListings.size

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = onOpenAddListing,
                icon = {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null
                    )
                },
                text = {
                    Text("Add Food")
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            contentPadding = PaddingValues(
                start = 24.dp,
                top = innerPadding.calculateTopPadding() + 24.dp,
                end = 24.dp,
                bottom = innerPadding.calculateBottomPadding() + 112.dp
            ),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "FreshGuard Home",
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Text(
                        text = "Your context-aware food rescue dashboard highlights what should be donated first, why it is urgent, and where you can act next.",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            item {
                ContextAlertCard(
                    title = "Warm conditions detected",
                    message = "A milder evening in Clayton shortens the safe pickup window for chilled and prepared food. Prioritise same-day collection for your two most time-sensitive listings."
                )
            }

            item {
                FreshnessOverviewCard(
                    monitoredItems = monitoredItems,
                    urgentCount = urgentCount
                )
            }

            item {
                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Quick actions",
                        style = MaterialTheme.typography.titleLarge
                    )

                    HomeQuickActionCard(
                        title = "My Listings",
                        subtitle = "Review your active items and update donation timing",
                        icon = Icons.Outlined.Inventory2,
                        onClick = onOpenMyListings
                    )

                    HomeQuickActionCard(
                        title = "Discover",
                        subtitle = "Browse nearby rescue opportunities and compare urgency",
                        icon = Icons.Default.Search,
                        onClick = onOpenDiscover
                    )
                }
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = "Urgent today",
                            style = MaterialTheme.typography.titleLarge
                        )
                        Text(
                            text = "Open the items most affected by timing, storage, and local conditions.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    TextButton(onClick = onOpenMyListings) {
                        Text("View all")
                    }
                }
            }

            items(urgentListings) { listing ->
                UrgentTodayCard(
                    listing = listing,
                    onOpenDetail = {
                        onOpenListingDetail(listing.id)
                    }
                )
            }

            item {
                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }
}

@Composable
private fun FreshnessOverviewCard(
    monitoredItems: Int,
    urgentCount: Int
) {
    val safeItems = (monitoredItems - urgentCount).coerceAtLeast(0)
    val readiness = if (monitoredItems == 0) {
        0f
    } else {
        safeItems.toFloat() / monitoredItems.toFloat()
    }
    val readinessPercent = (readiness * 100).toInt()

    FreshGuardCard(
        containerColor = MaterialTheme.colorScheme.secondaryContainer,
        contentColor = MaterialTheme.colorScheme.onSecondaryContainer
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Freshness overview",
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = "$readinessPercent% of your current items are still comfortably inside the safe donation window. The rest need faster action because of storage limits or same-day expiry.",
                style = MaterialTheme.typography.bodyMedium
            )
        }

        LinearProgressIndicator(
            progress = { readiness },
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            DashboardMetric(
                title = "Monitored",
                value = monitoredItems.toString(),
                subtitle = "active items"
            )
            DashboardMetric(
                title = "Urgent",
                value = urgentCount.toString(),
                subtitle = "need action today"
            )
            DashboardMetric(
                title = "Next check",
                value = "7:30 PM",
                subtitle = "pickup review"
            )
        }
    }
}

@Composable
private fun DashboardMetric(
    title: String,
    value: String,
    subtitle: String
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.78f)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
private fun UrgentTodayCard(
    listing: FoodListing,
    onOpenDetail: () -> Unit
) {
    FreshGuardCard(
        onClick = onOpenDetail,
        contentSpacing = 12.dp
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = listing.title,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "${listing.expiryText} • ${listing.pickupLocation}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.width(12.dp))
            UrgencyChip(urgency = listing.urgency)
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.Top
        ) {
            Icon(
                imageVector = Icons.Outlined.Schedule,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(
                    text = "Context note",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = listing.notes,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        Button(
            onClick = onOpenDetail,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Review details")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    FreshGuardTheme {
        HomeScreen(
            onOpenDiscover = {},
            onOpenMyListings = {},
            onOpenAddListing = {},
            onOpenListingDetail = {}
        )
    }
}
