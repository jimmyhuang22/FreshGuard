package com.example.freshguard.data

data class FoodListing(
    val id: String,
    val title: String,
    val category: String = "",
    val quantity: String,
    val expiryText: String,
    val pickupLocation: String,
    val pickupWindow: String = "",
    val urgency: String,
    val contextNote: String = "",
    val notes: String
)
