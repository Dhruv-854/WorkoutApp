package com.dhruv.a7minuteworkout.model

import androidx.compose.ui.res.painterResource
import com.dhruv.a7minuteworkout.R

class GetExerciseImpl : getExcercise {
    override fun getExercise(): List<ExerciseModel> {
        return listOf(
            ExerciseModel(
                id = 1,
                exerciseName = "plank",
                image = R.drawable.ic_plank,
                isCompleted = false,
                isSelected = false
            ),
            ExerciseModel(
                id = 2,
                exerciseName = "push up",
                image = R.drawable.ic_push_up,
                isCompleted = false,
                isSelected = false
            ),
            ExerciseModel(
                id = 3,
                exerciseName = "squat",
                image = R.drawable.ic_squat,
                isCompleted = false,
                isSelected = false
            ),
            ExerciseModel(
                id = 4,
                exerciseName = "lunge",
                image = R.drawable.ic_lunge,
                isCompleted = false,
                isSelected = false
            ),
            ExerciseModel(
                id = 5,
                exerciseName = "high knees",
                image = R.drawable.ic_high_knees_running_in_place,
                isCompleted = false,
                isSelected = false
            ),
            ExerciseModel(
                id = 6,
                exerciseName = "step up onto chair",
                image = R.drawable.ic_step_up_onto_chair,
                isCompleted = false,
                isSelected = false
            ),
            ExerciseModel(
                id = 7,
                exerciseName = "triceps dip",
                image = R.drawable.ic_triceps_dip_on_chair,
                isCompleted = false,
                isSelected = false
            ),
            ExerciseModel(
                id = 8,
                exerciseName = "jumping jacks",
                image = R.drawable.ic_jumping_jacks,
                isCompleted = false,
                isSelected = false
            ),
            ExerciseModel(
                id = 9,
                exerciseName = "push up and rotate",
                image = R.drawable.ic_push_up_and_rotation,
                isCompleted = false,
                isSelected = false
            ),
            ExerciseModel(
                id = 10,
                exerciseName = "side plank",
                image = R.drawable.ic_side_plank,
                isCompleted = false,
                isSelected = false
            ),
            ExerciseModel(
                id = 11,
                exerciseName = "wall sit",
                image = R.drawable.ic_wall_sit,
                isCompleted = false,
                isSelected = false
            ),
            ExerciseModel(
                id = 12,
                exerciseName = "Step up onto chair",
                image = R.drawable.ic_step_up_onto_chair,
                isCompleted = false,
                isSelected = false
            )
        )
    }
}