package com.example.freshguard.data

data class ChatMessage(
    val id: String,
    val senderName: String,
    val content: String,
    val timestamp: String,
    val isCurrentUser: Boolean
)
