package com.example.freshguard.data

object MockData {

    val myListings = listOf(
        FoodListing(
            id = "milk_001",
            title = "Milk",
            quantity = "2 bottles",
            expiryText = "Expires tonight",
            pickupLocation = "Clayton",
            urgency = "High",
            notes = "Please keep refrigerated. Best picked up before 8 PM."
        ),
        FoodListing(
            id = "banana_002",
            title = "Bananas",
            quantity = "6 pieces",
            expiryText = "Best within 1 day",
            pickupLocation = "Monash area",
            urgency = "Medium",
            notes = "Slightly ripe but still good for smoothies or baking."
        ),
        FoodListing(
            id = "sandwich_003",
            title = "Sandwiches",
            quantity = "3 packs",
            expiryText = "Urgent pickup today",
            pickupLocation = "Near campus",
            urgency = "High",
            notes = "Prepared this morning. Please collect as soon as possible."
        ),
        FoodListing(
            id = "bread_004",
            title = "Wholemeal Bread",
            quantity = "1 loaf",
            expiryText = "Use within 2 days",
            pickupLocation = "Caulfield",
            urgency = "Low",
            notes = "Unopened loaf, stored at room temperature."
        )
    )

    fun findListingById(listingId: String): FoodListing? {
        return myListings.find { it.id == listingId }
    }
}