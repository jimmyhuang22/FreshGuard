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

    val discoverListings = listOf(
        FoodListing(
            id = "yogurt_101",
            title = "Greek Yogurt",
            quantity = "4 cups",
            expiryText = "Best before tomorrow",
            pickupLocation = "Clayton South",
            urgency = "High",
            notes = "Still sealed. Please bring an insulated bag if possible."
        ),
        FoodListing(
            id = "muffin_102",
            title = "Blueberry Muffins",
            quantity = "5 pieces",
            expiryText = "Best today",
            pickupLocation = "Notting Hill",
            urgency = "Medium",
            notes = "Fresh from this morning. Suitable for quick pickup."
        ),
        FoodListing(
            id = "salad_103",
            title = "Garden Salad",
            quantity = "2 bowls",
            expiryText = "Pickup within 4 hours",
            pickupLocation = "Near campus",
            urgency = "High",
            notes = "Contains leafy greens and dressing on the side."
        ),
        FoodListing(
            id = "apple_104",
            title = "Apples",
            quantity = "8 pieces",
            expiryText = "Good for 3 more days",
            pickupLocation = "Oakleigh",
            urgency = "Low",
            notes = "Great for snacks or lunch boxes."
        ),
        FoodListing(
            id = "rice_105",
            title = "Cooked Rice",
            quantity = "2 containers",
            expiryText = "Pickup tonight",
            pickupLocation = "Clayton",
            urgency = "Medium",
            notes = "Cooked earlier today. Refrigerated after cooling."
        )
    )

    private val allListings = myListings + discoverListings

    fun findListingById(listingId: String): FoodListing? {
        return allListings.find { it.id == listingId }
    }
}