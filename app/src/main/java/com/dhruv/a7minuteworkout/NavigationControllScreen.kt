package com.dhruv.a7minuteworkout

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavigationControlScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val vm : ExerciseViewModel = viewModel()

    NavHost(navController = navController, startDestination = Screen.StartScreen.route) {
        composable(Screen.StartScreen.route) {
            StartScreen(modifier , navController , vm)
        }
        composable(Screen.ExerciseScreen.route) {
            ExerciseScreen(modifier , navController , vm)
        }
        composable(Screen.FirstExercise.route) {
            FirstExercise(modifier , navController , vm)
        }
    }
}

sealed class Screen (val route : String){
    object StartScreen : Screen("Start_Screen")
    object ExerciseScreen : Screen("Exercise_Screen")
    object FirstExercise : Screen( "first_screen")
}

@Preview
@Composable
private fun NavPreview() {
    NavigationControlScreen()
}