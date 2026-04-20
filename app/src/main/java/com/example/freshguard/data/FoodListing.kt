package com.example.freshguard.data

data class FoodListing(
    val id: String,
    val title: String,
    val quantity: String,
    val expiryText: String,
    val pickupLocation: String,
    val urgency: String,
    val notes: String
)