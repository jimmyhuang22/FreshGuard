package com.example.freshguard.ui.screens.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.freshguard.data.ChatMessage
import com.example.freshguard.data.MockData
import com.example.freshguard.ui.components.FreshGuardTopBar
import com.example.freshguard.ui.theme.FreshGuardTheme

@Composable
fun ChatScreen(
    listingId: String,
    onBack: () -> Unit
) {
    val listing = MockData.findListingById(listingId)
    var draftMessage by rememberSaveable { mutableStateOf("") }
    val messages = remember(listingId) {
        mutableStateListOf<ChatMessage>().apply {
            addAll(MockData.chatMessagesFor(listingId))
        }
    }

    val title = if (listing == null) {
        "Chat"
    } else {
        "Chat with ${listing.donorName}"
    }
    val subtitle = listing?.title ?: "FreshGuard conversation"

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            FreshGuardTopBar(
                title = title,
                onBackClick = onBack
            )
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                OutlinedTextField(
                    value = draftMessage,
                    onValueChange = { draftMessage = it },
                    modifier = Modifier.weight(1f),
                    label = { Text("Type a message") },
                    minLines = 1,
                    maxLines = 3
                )

                Button(
                    onClick = {
                        if (draftMessage.isNotBlank()) {
                            messages += ChatMessage(
                                id = "local_${messages.size}",
                                senderName = "You",
                                content = draftMessage.trim(),
                                timestamp = "Now",
                                isCurrentUser = true
                            )
                            draftMessage = ""
                        }
                    }
                ) {
                    Text("Send")
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = subtitle,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Use this prototype chat flow to confirm timing, pickup location, and any freshness instructions before the handoff.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(bottom = 12.dp)
            ) {
                items(messages, key = { it.id }) { message ->
                    ChatBubble(message = message)
                }
            }
        }
    }
}

@Composable
private fun ChatBubble(
    message: ChatMessage
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (message.isCurrentUser) Arrangement.End else Arrangement.Start
    ) {
        Column(
            modifier = Modifier
                .widthIn(max = 280.dp)
                .clip(MaterialTheme.shapes.medium)
                .background(
                    if (message.isCurrentUser) {
                        MaterialTheme.colorScheme.primaryContainer
                    } else {
                        MaterialTheme.colorScheme.surfaceVariant
                    }
                )
                .padding(horizontal = 14.dp, vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = message.senderName,
                style = MaterialTheme.typography.labelMedium,
                color = if (message.isCurrentUser) {
                    MaterialTheme.colorScheme.onPrimaryContainer
                } else {
                    MaterialTheme.colorScheme.onSurfaceVariant
                }
            )
            Text(
                text = message.content,
                style = MaterialTheme.typography.bodyMedium,
                color = if (message.isCurrentUser) {
                    MaterialTheme.colorScheme.onPrimaryContainer
                } else {
                    MaterialTheme.colorScheme.onSurface
                }
            )
            Text(
                text = message.timestamp,
                style = MaterialTheme.typography.labelSmall,
                color = if (message.isCurrentUser) {
                    MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.78f)
                } else {
                    MaterialTheme.colorScheme.onSurfaceVariant
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ChatScreenPreview() {
    FreshGuardTheme {
        ChatScreen(
            listingId = "milk_001",
            onBack = {}
        )
    }
}
