package com.example.freshguard.data

object MockData {

    val myListings = listOf(
        FoodListing(
            id = "milk_001",
            title = "Milk",
            donorName = "Maya Chen",
            category = "Dairy",
            quantity = "2 bottles",
            expiryText = "Expires tonight",
            pickupLocation = "Clayton",
            pickupWindow = "Today, 5:30 PM - 7:30 PM",
            urgency = "High",
            contextNote = "Chilled dairy should be collected before the warmer evening window shortens its safe handoff time.",
            notes = "Please keep refrigerated. Best picked up before 8 PM."
        ),
        FoodListing(
            id = "banana_002",
            title = "Bananas",
            donorName = "Liam Foster",
            category = "Fruit",
            quantity = "6 pieces",
            expiryText = "Best within 1 day",
            pickupLocation = "Monash area",
            pickupWindow = "Today or tomorrow, before 6:00 PM",
            urgency = "Medium",
            contextNote = "Ripe fruit is more likely to be rescued today when receivers are looking for snack or smoothie ingredients.",
            notes = "Slightly ripe but still good for smoothies or baking."
        ),
        FoodListing(
            id = "sandwich_003",
            title = "Sandwiches",
            donorName = "Nina Patel",
            category = "Prepared Meals",
            quantity = "3 packs",
            expiryText = "Urgent pickup today",
            pickupLocation = "Near campus",
            pickupWindow = "Today, 2:00 PM - 4:30 PM",
            urgency = "High",
            contextNote = "Prepared food has the narrowest handoff window, so same-day pickup is strongly recommended.",
            notes = "Prepared this morning. Please collect as soon as possible."
        ),
        FoodListing(
            id = "bread_004",
            title = "Wholemeal Bread",
            donorName = "Oliver Tan",
            category = "Bakery",
            quantity = "1 loaf",
            expiryText = "Use within 2 days",
            pickupLocation = "Caulfield",
            pickupWindow = "Tomorrow, 10:00 AM - 1:00 PM",
            urgency = "Low",
            contextNote = "Shelf-stable bakery items can stay available a little longer, which makes scheduling easier.",
            notes = "Unopened loaf, stored at room temperature."
        )
    )

    val discoverListings = listOf(
        FoodListing(
            id = "yogurt_101",
            title = "Greek Yogurt",
            donorName = "Sophie Nguyen",
            category = "Dairy",
            quantity = "4 cups",
            expiryText = "Best before tomorrow",
            pickupLocation = "Clayton South",
            pickupWindow = "Tonight, 6:00 PM - 8:00 PM",
            urgency = "High",
            contextNote = "The donor has kept these sealed and chilled, but the pickup window is short because they expire tomorrow.",
            notes = "Still sealed. Please bring an insulated bag if possible."
        ),
        FoodListing(
            id = "muffin_102",
            title = "Blueberry Muffins",
            donorName = "Ava Robinson",
            category = "Bakery",
            quantity = "5 pieces",
            expiryText = "Best today",
            pickupLocation = "Notting Hill",
            pickupWindow = "Today, 3:00 PM - 6:30 PM",
            urgency = "Medium",
            contextNote = "Fresh bakery items are most likely to be claimed quickly in the late afternoon pickup period.",
            notes = "Fresh from this morning. Suitable for quick pickup."
        ),
        FoodListing(
            id = "salad_103",
            title = "Garden Salad",
            donorName = "Marcus Lee",
            category = "Prepared Meals",
            quantity = "2 bowls",
            expiryText = "Pickup within 4 hours",
            pickupLocation = "Near campus",
            pickupWindow = "Today, within the next 4 hours",
            urgency = "High",
            contextNote = "Leafy greens lose freshness quickly, so nearby same-day collection is the safest rescue option.",
            notes = "Contains leafy greens and dressing on the side."
        ),
        FoodListing(
            id = "apple_104",
            title = "Apples",
            donorName = "Ella Martin",
            category = "Fruit",
            quantity = "8 pieces",
            expiryText = "Good for 3 more days",
            pickupLocation = "Oakleigh",
            pickupWindow = "Tomorrow, 9:00 AM - 12:00 PM",
            urgency = "Low",
            contextNote = "Whole fruit remains stable for longer, which gives receivers more flexibility to collect it later.",
            notes = "Great for snacks or lunch boxes."
        ),
        FoodListing(
            id = "rice_105",
            title = "Cooked Rice",
            donorName = "Zoe Walker",
            category = "Prepared Meals",
            quantity = "2 containers",
            expiryText = "Pickup tonight",
            pickupLocation = "Clayton",
            pickupWindow = "Tonight, 5:00 PM - 7:00 PM",
            urgency = "Medium",
            contextNote = "Cooked food is safest when the handoff happens promptly after refrigeration and before a late pickup delay.",
            notes = "Cooked earlier today. Refrigerated after cooling."
        )
    )

    private val allListings = myListings + discoverListings

    fun findListingById(listingId: String): FoodListing? {
        return allListings.find { it.id == listingId }
    }

    fun chatMessagesFor(listingId: String): List<ChatMessage> {
        val listing = findListingById(listingId)
        val donorName = listing?.donorName ?: "FreshGuard Donor"

        return listOf(
            ChatMessage(
                id = "${listingId}_1",
                senderName = donorName,
                content = "Hi! This listing is still available if you can collect it during the posted pickup window.",
                timestamp = "5:12 PM",
                isCurrentUser = false
            ),
            ChatMessage(
                id = "${listingId}_2",
                senderName = "You",
                content = "Thanks, I am nearby and should be able to reach the pickup point in about 20 minutes.",
                timestamp = "5:14 PM",
                isCurrentUser = true
            ),
            ChatMessage(
                id = "${listingId}_3",
                senderName = donorName,
                content = "Great. Please message me when you arrive so I can bring the food down straight away.",
                timestamp = "5:15 PM",
                isCurrentUser = false
            )
        )
    }
}
