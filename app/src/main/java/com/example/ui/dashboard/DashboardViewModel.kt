package com.example.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.database.entities.HabitEntity
import com.example.repository.HabitRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val repository: HabitRepository
) : ViewModel() {

    val habits: StateFlow<List<HabitEntity>> = repository.getAllHabits()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addSampleHabit() {
        viewModelScope.launch {
            repository.insertHabit(
                HabitEntity(
                    title = "Drink Water",
                    category = "Health",
                    icon = "water_drop",
                    color = "#03DAC6",
                    dailyGoal = 8,
                    repeatSchedule = "Daily",
                    reminderTime = "09:00 AM",
                    notes = "Stay hydrated"
                )
            )
        }
    }

    class Factory(private val repository: HabitRepository) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DashboardViewModel::class.java)) {
                return DashboardViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
