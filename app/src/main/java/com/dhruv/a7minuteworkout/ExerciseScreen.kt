package com.dhruv.a7minuteworkout

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.dhruv.a7minuteworkout.component.GetReadyComponentText
import com.dhruv.a7minuteworkout.component.TimerComponent
import com.dhruv.a7minuteworkout.component.TopBarDesign

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ExerciseScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    vm: ExerciseViewModel,
) {
    Scaffold(
        topBar = {
            TopBarDesign(text = "7 Minute Workout") {
                vm.resetExerciseIndex()
                vm.stopTimer()
                navController.navigate(Screen.StartScreen.route) {
                    popUpTo(Screen.StartScreen.route) {
                        inclusive = true
                    }
                }
            }
        }
    ) {
        Box(modifier = modifier.padding(it)) {
            Column(
                modifier = modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                GetReadyComponentText(text = "Get Ready for Exercise")
                TimerComponent(
                    vm = vm,
                    progress = 0f,
                    timeLeft = 10,
                    timeLeftFix = 10,
                    timeFixInMillis = 10000,
                    onStartClick = {
                        navController.navigate(Screen.FirstExercise.route)
                        vm.startTimer()
                    },
                    toast = "Get Ready for Exercise"
                )
            }
        }
    }
}

