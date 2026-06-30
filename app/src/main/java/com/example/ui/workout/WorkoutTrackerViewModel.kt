package com.example.ui.workout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.database.entities.WorkoutEntity
import com.example.repository.WorkoutRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class WorkoutTrackerViewModel(
    private val repository: WorkoutRepository
) : ViewModel() {

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    val workouts: StateFlow<List<WorkoutEntity>> = repository.getAllWorkouts()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addWorkout(type: String, durationMinutes: Int, calories: Int, notes: String) {
        viewModelScope.launch {
            repository.insertWorkout(
                WorkoutEntity(
                    type = type,
                    durationMinutes = durationMinutes,
                    calories = calories,
                    date = dateFormat.format(Date()),
                    notes = notes
                )
            )
        }
    }

    class Factory(private val repository: WorkoutRepository) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(WorkoutTrackerViewModel::class.java)) {
                return WorkoutTrackerViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
