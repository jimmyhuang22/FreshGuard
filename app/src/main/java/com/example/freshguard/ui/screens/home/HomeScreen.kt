package com.example.freshguard.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.freshguard.ui.components.ContextAlertCard
import com.example.freshguard.ui.components.HomeQuickActionCard

@Composable
fun HomeScreen(
    onOpenDiscover: () -> Unit,
    onOpenMyListings: () -> Unit,
    onOpenAddListing: () -> Unit,
    onOpenStats: () -> Unit,
    onOpenProfile: () -> Unit
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = "Welcome back",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Here is your FreshGuard snapshot for today.",
            style = MaterialTheme.typography.bodyLarge
        )

        ContextAlertCard(
            title = "Priority Alert",
            message = "2 items are expiring within 24 hours. Consider listing them earlier today."
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Quick actions",
                style = MaterialTheme.typography.titleLarge
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                HomeQuickActionCard(
                    title = "Discover",
                    subtitle = "Browse nearby food listings",
                    icon = Icons.Default.Search,
                    onClick = onOpenDiscover,
                    modifier = Modifier.weight(1f)
                )

                HomeQuickActionCard(
                    title = "My Listings",
                    subtitle = "Manage your food posts",
                    icon = Icons.Default.Home,
                    onClick = onOpenMyListings,
                    modifier = Modifier.weight(1f)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                HomeQuickActionCard(
                    title = "Add Listing",
                    subtitle = "Create a new food post",
                    icon = Icons.Default.Add,
                    onClick = onOpenAddListing,
                    modifier = Modifier.weight(1f)
                )

                HomeQuickActionCard(
                    title = "Stats",
                    subtitle = "View your rescue progress",
                    icon = Icons.Default.BarChart,
                    onClick = onOpenStats,
                    modifier = Modifier.weight(1f)
                )
            }

            HomeQuickActionCard(
                title = "Profile",
                subtitle = "Update your preferences",
                icon = Icons.Default.Person,
                onClick = onOpenProfile
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Recent listing activity",
                style = MaterialTheme.typography.titleLarge
            )

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    RecentItemRow(
                        title = "Milk",
                        subtitle = "Expires tonight • Clayton pickup"
                    )

                    HorizontalDivider()

                    RecentItemRow(
                        title = "Bananas",
                        subtitle = "Best within 1 day • Monash area"
                    )

                    HorizontalDivider()

                    RecentItemRow(
                        title = "Sandwiches",
                        subtitle = "Urgent pickup • Near campus"
                    )
                }
            }
        }
    }
}

@Composable
private fun RecentItemRow(
    title: String,
    subtitle: String
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}