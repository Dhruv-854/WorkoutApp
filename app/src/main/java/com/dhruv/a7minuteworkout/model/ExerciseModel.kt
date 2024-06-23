package com.dhruv.a7minuteworkout.model

import androidx.compose.ui.graphics.painter.Painter

data class ExerciseModel(
    var id: Int,
    var exerciseName: String,
    var image: Int?,
    var isCompleted: Boolean,
    var isSelected: Boolean
)