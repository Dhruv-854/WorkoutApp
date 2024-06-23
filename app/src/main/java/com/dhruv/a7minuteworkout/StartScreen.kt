package com.dhruv.a7minuteworkout

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun StartScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    vm: ExerciseViewModel
) {
    val context = LocalContext.current
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = modifier.fillMaxWidth(),
        ) {
            Row(
                modifier = modifier.fillMaxWidth(),
            ) {
                Text(text = "7", fontSize = 150.sp , fontWeight = FontWeight.Bold)
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .align(Alignment.CenterVertically),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Minute" , fontSize = 60.sp)
                    Text(text = "Workout" , fontSize = 60.sp)
                }
            }
        }
        Spacer(modifier = modifier.height(100.dp))
        Box(
            modifier = modifier.size(150.dp, 150.dp).clip(RoundedCornerShape(100.dp))
        ) {
            Canvas(
                modifier = Modifier
                    .matchParentSize()
                    .clickable {
                        navController.navigate(Screen.ExerciseScreen.route)
                        vm.startTimer()

                    }
            ) {
                drawCircle(
                    color = Color.LightGray,
                    radius = size.minDimension/2
                )
                // Draw the border
                drawCircle(
                    color = Color.Green,
                    radius = size.minDimension/2,
                    style = Stroke(width = 10f)
                )
            }
            Text(
                text = "Start",
                color = Color.Green,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}