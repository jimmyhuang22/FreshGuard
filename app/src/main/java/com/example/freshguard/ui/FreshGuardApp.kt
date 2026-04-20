package com.example.freshguard.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.freshguard.navigation.BottomNavItem
import com.example.freshguard.navigation.Screen
import com.example.freshguard.ui.screens.auth.LoginScreen
import com.example.freshguard.ui.screens.auth.SignUpScreen
import com.example.freshguard.ui.screens.discover.DiscoverScreen
import com.example.freshguard.ui.screens.home.HomeScreen
import com.example.freshguard.ui.screens.listings.AddEditListingScreen
import com.example.freshguard.ui.screens.listings.ListingDetailScreen
import com.example.freshguard.ui.screens.listings.MyListingsScreen
import com.example.freshguard.ui.screens.profile.ProfileScreen
import com.example.freshguard.ui.screens.stats.StatsScreen

@Composable
fun FreshGuardApp() {
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    val bottomNavRoutes = setOf(
        Screen.Home.route,
        Screen.Discover.route,
        Screen.Stats.route,
        Screen.Profile.route
    )

    val bottomItems = listOf(
        BottomNavItem(Screen.Home.route, "Home", Icons.Default.Home),
        BottomNavItem(Screen.Discover.route, "Discover", Icons.Default.Search),
        BottomNavItem(Screen.Stats.route, "Stats", Icons.Default.BarChart),
        BottomNavItem(Screen.Profile.route, "Profile", Icons.Default.Person)
    )

    Scaffold(
        bottomBar = {
            if (currentRoute in bottomNavRoutes) {
                NavigationBar {
                    bottomItems.forEach { item ->
                        NavigationBarItem(
                            selected = currentRoute == item.route,
                            onClick = {
                                if (currentRoute != item.route) {
                                    navController.navigate(item.route) {
                                        popUpTo(Screen.Home.route) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            },
                            icon = {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.title
                                )
                            },
                            label = {
                                Text(item.title)
                            }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Login.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Login.route) {
                LoginScreen(
                    onLogin = {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Login.route) { inclusive = true }
                        }
                    },
                    onNavigateToSignUp = {
                        navController.navigate(Screen.SignUp.route)
                    }
                )
            }

            composable(Screen.SignUp.route) {
                SignUpScreen(
                    onCreateAccount = {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Login.route) { inclusive = true }
                        }
                    },
                    onBack = {
                        navController.popBackStack()
                    }
                )
            }

            composable(Screen.Home.route) {
                HomeScreen(
                    onOpenDiscover = {
                        navController.navigate(Screen.Discover.route)
                    },
                    onOpenMyListings = {
                        navController.navigate(Screen.MyListings.route)
                    },
                    onOpenAddListing = {
                        navController.navigate(Screen.AddEditListing.route)
                    },
                    onOpenStats = {
                        navController.navigate(Screen.Stats.route)
                    },
                    onOpenProfile = {
                        navController.navigate(Screen.Profile.route)
                    }
                )
            }

            composable(Screen.MyListings.route) {
                MyListingsScreen(
                    onBack = {
                        navController.popBackStack()
                    },
                    onAddListing = {
                        navController.navigate(Screen.AddEditListing.route)
                    },
                    onOpenDetail = { listingId ->
                        navController.navigate(Screen.ListingDetail.createRoute(listingId))
                    }
                )
            }

            composable(Screen.AddEditListing.route) {
                AddEditListingScreen(
                    onBack = {
                        navController.popBackStack()
                    },
                    onSaveDraft = {
                        navController.popBackStack()
                    },
                    onPublish = {
                        navController.popBackStack()
                    }
                )
            }

            composable(
                route = Screen.ListingDetail.route,
                arguments = listOf(
                    navArgument("listingId") {
                        type = NavType.StringType
                    }
                )
            ) { backStackEntry ->
                val listingId = backStackEntry.arguments?.getString("listingId").orEmpty()

                ListingDetailScreen(
                    listingId = listingId,
                    onBack = {
                        navController.popBackStack()
                    }
                )
            }

            composable(Screen.Discover.route) {
                DiscoverScreen(
                    onOpenDetail = { listingId ->
                        navController.navigate(Screen.ListingDetail.createRoute(listingId))
                    }
                )
            }

            composable(Screen.Stats.route) {
                StatsScreen()
            }

            composable(Screen.Profile.route) {
                ProfileScreen()
            }
        }
    }
}
