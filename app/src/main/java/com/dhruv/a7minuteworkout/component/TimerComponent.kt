package com.dhruv.a7minuteworkout.component

import android.os.CountDownTimer
import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dhruv.a7minuteworkout.ExerciseViewModel

@Composable
fun TimerComponent(
    modifier: Modifier = Modifier,
    vm: ExerciseViewModel,
    progress: Float,
    timeLeft: Int,
    onStartClick: () -> Unit,
    timeLeftFix: Int,
    timeFixInMillis: Long,
    toast: String
) {
    var progress by remember { mutableStateOf(progress) }
    var timeLeft by remember { mutableStateOf(timeLeft) }
    val isRunning by vm.isTimerRunning.collectAsState()
    val context = LocalContext.current
    val timer = remember {
        object : CountDownTimer(timeFixInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                progress += 1f / timeLeftFix
                timeLeft = (millisUntilFinished / 1000).toInt()
            }

            override fun onFinish() {
                progress = 0f
                timeLeft = timeLeftFix
                vm.stopTimer()
                Toast.makeText(context, toast, Toast.LENGTH_SHORT).show()
                onStartClick.invoke()
            }
        }
    }

    DisposableEffect(key1 = isRunning) {
        if (isRunning) {
            timer.start()
        } else {
            timer.cancel()
        }
        onDispose {
            timer.cancel()
        }
    }

    Box(
        modifier = modifier
            .padding(8.dp)
            .size(100.dp, 100.dp)
            .fillMaxSize()
            .clip(RoundedCornerShape(70.dp))
            .background(Color.White)
            .clickable {
                if (!isRunning) {
                    progress = 0f
                    timeLeft = timeLeftFix
                    vm.startTimer()
                } else {
                    vm.stopTimer()
                }
            },
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
        ) {
            val radius = size.minDimension / 2
            val innerRadius = radius - 15f

            // Draw the outer circle
            drawCircle(
                color = Color.Gray,
                radius = radius,
                style = Stroke(width = 30f)
            )

            // Draw the progress arc
            drawArc(
                color = Color.Green,
                startAngle = -90f,
                sweepAngle = progress * 360,
                useCenter = false,
                style = Stroke(width = 30f)
            )

            // Draw the inner circle
            drawCircle(
                color = Color.White,
                radius = innerRadius
            )

            // Draw the center circle
            drawCircle(
                color = Color.Green,
                radius = innerRadius / 2
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
        ) {
            Text(
                text = "$timeLeft",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}
