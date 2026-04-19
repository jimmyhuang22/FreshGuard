package com.example.freshguard.navigation

sealed class Screen(val route: String) {
    data object Login : Screen("login")
    data object SignUp : Screen("signup")
    data object Home : Screen("home")
    data object Discover : Screen("discover")
    data object Stats : Screen("stats")
    data object Profile : Screen("profile")
}
