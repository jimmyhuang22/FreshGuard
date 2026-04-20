package com.example.freshguard.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.freshguard.ui.theme.DonateSoonContainer
import com.example.freshguard.ui.theme.DonateSoonText
import com.example.freshguard.ui.theme.SafeContainer
import com.example.freshguard.ui.theme.SafeText
import com.example.freshguard.ui.theme.UrgentContainer
import com.example.freshguard.ui.theme.UrgentText

@Composable
fun UrgencyChip(
    urgency: String,
    modifier: Modifier = Modifier
) {
    val style = urgencyStyleFor(urgency)

    Surface(
        modifier = modifier,
        color = style.containerColor,
        contentColor = style.contentColor,
        shape = MaterialTheme.shapes.small
    ) {
        Text(
            text = style.label,
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(
                PaddingValues(horizontal = 12.dp, vertical = 6.dp)
            )
        )
    }
}

private fun urgencyStyleFor(urgency: String): UrgencyStyle {
    val normalized = urgency.trim().lowercase()

    return when {
        normalized.contains("urgent") || normalized.contains("high") -> {
            UrgencyStyle("Urgent", UrgentContainer, UrgentText)
        }
        normalized.contains("soon") || normalized.contains("medium") -> {
            UrgencyStyle("Donate Soon", DonateSoonContainer, DonateSoonText)
        }
        else -> {
            UrgencyStyle("Safe", SafeContainer, SafeText)
        }
    }
}

private data class UrgencyStyle(
    val label: String,
    val containerColor: Color,
    val contentColor: Color
)
