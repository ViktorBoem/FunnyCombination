package com.example.funnycombination.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.funnycombination.ui.screens.gameResultFetures.GameResultScreen
import com.example.funnycombination.ui.screens.gameCombinationFeatures.GameCombinationScreen
import com.example.funnycombination.ui.screens.highScoreFeatures.HighScoreScreen
import com.example.funnycombination.ui.screens.splashScreenFeatures.CustomSplashScreen
import com.example.funnycombination.ui.screens.mainMenuFeatures.MainMenuScreen
import com.example.funnycombination.ui.screens.privacyPolicyFeatures.PrivacyPolicyScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            CustomSplashScreen(navController = navController)
        }

        composable("main_menu") {
            MainMenuScreen(
                onPlayClick = { navController.navigate("game") },
                onHighScoreClick = { navController.navigate("highscores") },
                onPrivacyPolicyClick = { navController.navigate("privacy_policy") }
            )
        }

        composable("game") {
            GameCombinationScreen(
                onGameOver = { score, isNewRecord ->
                    navController.navigate("game_over/$score/$isNewRecord") {
                        popUpTo("game") { inclusive = true }
                    }
                }
            )
        }

        composable(
            "game_over/{score}/{isNewRecord}",
            arguments = listOf(
                navArgument("score") { type = NavType.IntType },
                navArgument("isNewRecord") { type = NavType.BoolType }
            )
        ) { backStackEntry ->
            val score = backStackEntry.arguments?.getInt("score") ?: 0
            val isNewRecord = backStackEntry.arguments?.getBoolean("isNewRecord") ?: false

            GameResultScreen(
                score = score,
                isNewRecord = isNewRecord,
                onPlayAgain = {
                    navController.navigate("game") {
                        popUpTo("main_menu")
                    }
                },
                onMainMenu = {
                    navController.navigate("main_menu") {
                        popUpTo("main_menu") { inclusive = true }
                    }
                }
            )
        }

        composable("highscores") {
            HighScoreScreen(onBack = { navController.popBackStack() })
        }

        composable("privacy_policy") {
            PrivacyPolicyScreen(onBack = { navController.popBackStack() })
        }
    }
}