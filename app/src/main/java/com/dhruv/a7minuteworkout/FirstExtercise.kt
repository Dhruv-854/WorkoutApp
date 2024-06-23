package com.dhruv.a7minuteworkout

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

import androidx.navigation.NavHostController
import com.dhruv.a7minuteworkout.component.GetReadyComponentText
import com.dhruv.a7minuteworkout.component.TimerComponent
import com.dhruv.a7minuteworkout.component.TopBarDesign


@SuppressLint("RestrictedApi")
@Composable
fun FirstExercise(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    vm: ExerciseViewModel,
) {
    val currentExercise by vm.currentExercise.collectAsState()

    Scaffold(
        topBar = {
            TopBarDesign(text = "Exercise ${currentExercise?.id ?: 0}") {
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
        Box(
            modifier = modifier.padding(it),
        ) {
            Column(
                modifier = modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = currentExercise?.image ?: 0),
                    contentDescription = null
                )
                Text(text = currentExercise?.exerciseName ?: "")
                TimerComponent(
                    vm = vm,
                    progress = 0f,
                    timeLeft = 30,
                    timeLeftFix = 30,
                    timeFixInMillis = 30000,
                    onStartClick = {
                        if (vm.isLastExercise()) {
                            vm.stopTimer()
                            navController.navigate(Screen.StartScreen.route) {
                                popUpTo(Screen.StartScreen.route) {
                                    inclusive = true
                                }
                            }
                        } else {
                            navController.navigate(Screen.ExerciseScreen.route) {
                                popUpTo(Screen.ExerciseScreen.route) {
                                    inclusive = true
                                }
                            }
                        }
                        vm.nextExercise()

                    },
                    toast = "Ready for Next!!\nClick to Start",
                )
//                Button(onClick = {
//                    if (vm.isLastExercise()) {
//                        vm.stopTimer()
//                        navController.navigate(Screen.StartScreen.route) {
//                            popUpTo(Screen.StartScreen.route) {
//                                inclusive = true
//                            }
//                        }
//                    } else {
//                        vm.nextExercise()
//                        navController.navigate(Screen.FirstExercise.route) {
//                            popUpTo(Screen.FirstExercise.route) {
//                                inclusive = true
//                            }
//                        }
//                    }
//                }) {
//                    Text(text = "Next Exercise!!")
//                }
            }
        }
    }
}
