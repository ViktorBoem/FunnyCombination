package com.example.funnycombination.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.funnycombination.ui.screens.splashScreenFeatures.CustomSplashScreen
import com.example.funnycombination.ui.screens.mainMenuFeatures.MainMenuScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            CustomSplashScreen(navController = navController)
        }
        composable("main_menu") {
            MainMenuScreen()
        }
    }
}