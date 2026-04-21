package com.example.freshguard.ui.screens.stats

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.freshguard.ui.components.BarChartItem
import com.example.freshguard.ui.components.FreshGuardCard
import com.example.freshguard.ui.components.SimpleBarChart
import com.example.freshguard.ui.components.StatSummaryCard
import com.example.freshguard.ui.theme.FreshGuardTheme

@Composable
fun StatsScreen() {
    var selectedRange by rememberSaveable { mutableStateOf("7-day") }
    val isWeekly = selectedRange == "7-day"
    val selectWeekly = {
        if (!isWeekly) {
            selectedRange = "7-day"
        }
    }
    val selectMonthly = {
        if (isWeekly) {
            selectedRange = "30-day"
        }
    }

    val rescuedSeries = if (isWeekly) {
        listOf(
            BarChartItem("Mon", 2f),
            BarChartItem("Tue", 4f),
            BarChartItem("Wed", 3f),
            BarChartItem("Thu", 5f),
            BarChartItem("Fri", 4f),
            BarChartItem("Sat", 6f),
            BarChartItem("Sun", 3f)
        )
    } else {
        listOf(
            BarChartItem("Week 1", 9f),
            BarChartItem("Week 2", 12f),
            BarChartItem("Week 3", 8f),
            BarChartItem("Week 4", 14f)
        )
    }

    val categoryBreakdown = if (isWeekly) {
        listOf(
            BarChartItem("Prepared", 5f),
            BarChartItem("Fruit", 3f),
            BarChartItem("Bakery", 4f),
            BarChartItem("Dairy", 2f)
        )
    } else {
        listOf(
            BarChartItem("Prepared", 16f),
            BarChartItem("Fruit", 10f),
            BarChartItem("Bakery", 11f),
            BarChartItem("Dairy", 7f)
        )
    }

    val rescuedCount = if (isWeekly) "7" else "43"
    val mealsEstimate = if (isWeekly) "19" else "112"
    val wasteReduced = if (isWeekly) "12.4 kg" else "48.7 kg"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Stats",
                style = MaterialTheme.typography.headlineMedium
            )

            Text(
                text = "Review your rescue impact over time, compare short-term and monthly activity, and identify which food categories are saved most often.",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            RangeSelectorButton(
                selected = isWeekly,
                label = "7-day",
                modifier = Modifier.weight(1f),
                onClick = selectWeekly
            )

            RangeSelectorButton(
                selected = !isWeekly,
                label = "30-day",
                modifier = Modifier.weight(1f),
                onClick = selectMonthly
            )
        }

        StatSummaryCard(
            title = "Items rescued",
            value = rescuedCount,
            subtitle = "Listings successfully matched in the selected range"
        )

        StatSummaryCard(
            title = "Estimated meals supported",
            value = mealsEstimate,
            subtitle = "Prototype estimate of portions redirected to receivers"
        )

        StatSummaryCard(
            title = "Estimated waste reduced",
            value = wasteReduced,
            subtitle = "Calculated from recorded quantities and item categories"
        )

        SimpleBarChart(
            title = if (isWeekly) {
                "Weekly rescued items"
            } else {
                "30-day rescued items"
            },
            items = rescuedSeries
        )

        SimpleBarChart(
            title = if (isWeekly) {
                "Category breakdown this week"
            } else {
                "Category breakdown this month"
            },
            items = categoryBreakdown
        )

        FreshGuardCard {
            Text(
                text = "Insights",
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = if (isWeekly) {
                    "Prepared meals are currently your strongest rescue category, which suggests same-day pickup coordination is one of the most valuable prototype features."
                } else {
                    "Across 30 days, prepared meals and bakery items dominate your rescue impact, which supports highlighting freshness messaging and pickup planning in the core flow."
                },
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = "The selected state above makes it easy to compare short-term behaviour with a broader monthly trend, which is useful for screenshot evidence in Step 3.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun RangeSelectorButton(
    selected: Boolean,
    label: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    if (selected) {
        Button(
            onClick = onClick,
            modifier = modifier
        ) {
            Text(label)
        }
    } else {
        OutlinedButton(
            onClick = onClick,
            modifier = modifier
        ) {
            Text(label)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun StatsScreenPreview() {
    FreshGuardTheme {
        StatsScreen()
    }
}
