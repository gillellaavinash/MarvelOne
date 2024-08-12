package com.example.marvelone

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Navigation(viewModel: ImagesViewModel) {
    val navHostController = rememberNavController()
    NavHost(navController = navHostController, startDestination = "splash_screen"){
        composable("splash_screen"){
            SplashScreen(navController = navHostController)
        }
        composable("game_screen"){
            GameScreen(navController = navHostController,viewModel = viewModel)
        }
        composable("main_screen") {
            MainScreen(navController = navHostController,viewModel = viewModel)
        }
        composable("quiz_screen"){
            QuizScreen(navController = navHostController,viewModel = viewModel)
        }
        composable("score_screen/{score}",
                arguments = listOf(
                navArgument("score")
                { type = NavType.IntType },
                    )

        ){backStackEntry ->
            Score(
                backStackEntry = backStackEntry
            )
        }
    }
}