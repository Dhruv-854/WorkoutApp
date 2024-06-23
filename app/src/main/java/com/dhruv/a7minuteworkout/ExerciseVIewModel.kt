package com.dhruv.a7minuteworkout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dhruv.a7minuteworkout.model.ExerciseModel
import com.dhruv.a7minuteworkout.model.GetExerciseImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
class ExerciseViewModel : ViewModel() {
    private val getExerciseImpl = GetExerciseImpl()
    private val _exercises = MutableStateFlow<List<ExerciseModel>>(emptyList())
    private val _currentIndex = MutableStateFlow(0)
    private val _isTimerRunning = MutableStateFlow(false)
    private val _timerFinished = MutableStateFlow(false)

    val currentExercise: StateFlow<ExerciseModel?> = _exercises
        .combine(_currentIndex) { exercises, currentIndex ->
            if (exercises.isNotEmpty()) {
                exercises[currentIndex]
            } else {
                null
            }
        }
        .stateIn(viewModelScope, SharingStarted.Eagerly, null)

    val isTimerRunning: StateFlow<Boolean> = _isTimerRunning.asStateFlow()
    val timerFinished: StateFlow<Boolean> = _timerFinished.asStateFlow()

    init {
        loadExercises()
    }

    private fun loadExercises() {
        viewModelScope.launch {
            val result = getExerciseImpl.getExercise()
            _exercises.value = result
        }
    }

    fun nextExercise() {
        if (_currentIndex.value < _exercises.value.size - 1) {
            _currentIndex.value += 1
            _isTimerRunning.value = true
            _timerFinished.value = false
        } else {
            _timerFinished.value = true
        }
    }

    fun resetExerciseIndex() {
        _currentIndex.value = 0
        _isTimerRunning.value = false
        _timerFinished.value = false
    }

    fun startTimer() {
        _isTimerRunning.value = true
    }

    fun stopTimer() {
        _isTimerRunning.value = false
    }

    fun timerFinished() {
        _isTimerRunning.value = false
        _timerFinished.value = true
    }
    fun isLastExercise(): Boolean {
        return _currentIndex.value == _exercises.value.size - 1
    }
}

