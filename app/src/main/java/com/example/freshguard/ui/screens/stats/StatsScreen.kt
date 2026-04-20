package com.example.freshguard.ui.screens.stats

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.freshguard.ui.components.BarChartItem
import com.example.freshguard.ui.components.SimpleBarChart
import com.example.freshguard.ui.components.StatSummaryCard

@Composable
fun StatsScreen() {
    val scrollState = rememberScrollState()

    val weeklyItems = listOf(
        BarChartItem("Mon", 2f),
        BarChartItem("Tue", 4f),
        BarChartItem("Wed", 3f),
        BarChartItem("Thu", 5f),
        BarChartItem("Fri", 4f),
        BarChartItem("Sat", 6f),
        BarChartItem("Sun", 3f)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Stats",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Track your food rescue progress and impact over time.",
            style = MaterialTheme.typography.bodyLarge
        )

        StatSummaryCard(
            title = "Items rescued",
            value = "28",
            subtitle = "Total food listings successfully shared"
        )

        StatSummaryCard(
            title = "This week",
            value = "7",
            subtitle = "Listings created in the last 7 days"
        )

        StatSummaryCard(
            title = "Estimated waste reduced",
            value = "12.4 kg",
            subtitle = "Prototype estimate based on saved food quantity"
        )

        SimpleBarChart(
            title = "Weekly listing activity",
            items = weeklyItems
        )

        Card {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "Insights",
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = "Your highest listing activity is currently on Saturday, suggesting weekend pickups may be the most effective time to reduce food waste.",
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = "High-urgency items are more likely to be listed close to expiry, which supports the app's context-aware reminder concept.",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}