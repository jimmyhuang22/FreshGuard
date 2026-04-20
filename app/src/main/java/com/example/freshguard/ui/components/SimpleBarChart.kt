package com.example.freshguard.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class BarChartItem(
    val label: String,
    val value: Float
)

@Composable
fun SimpleBarChart(
    title: String,
    items: List<BarChartItem>,
    modifier: Modifier = Modifier
) {
    val maxValue = (items.maxOfOrNull { it.value } ?: 1f).coerceAtLeast(1f)

    Card(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium
            )

            items.forEach { item ->
                Column(
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = item.label,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = item.value.toInt().toString(),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }

                    LinearProgressIndicator(
                        progress = { item.value / maxValue },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}