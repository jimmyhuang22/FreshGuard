package com.example.freshguard.navigation

sealed class Screen(val route: String) {
    data object Login : Screen("login")
    data object SignUp : Screen("signup")
    data object Home : Screen("home")
    data object MyListings : Screen("my_listings")
    data object ListingDetail : Screen("listing_detail/{listingId}") {
        fun createRoute(listingId: String): String {
            return "listing_detail/$listingId"
        }
    }
    data object Discover : Screen("discover")
    data object Stats : Screen("stats")
    data object Profile : Screen("profile")
}