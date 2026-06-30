package com.example.ui.habits

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.database.entities.HabitEntity
import com.example.repository.HabitRepository
import kotlinx.coroutines.launch

class AddHabitViewModel(
    private val repository: HabitRepository
) : ViewModel() {

    fun saveHabit(
        title: String,
        category: String,
        dailyGoal: Int,
        repeatSchedule: String,
        reminderTime: String,
        notes: String
    ) {
        viewModelScope.launch {
            repository.insertHabit(
                HabitEntity(
                    title = title,
                    category = category,
                    icon = "star", // Default for now
                    color = "#6C63FF", // Primary
                    dailyGoal = dailyGoal,
                    repeatSchedule = repeatSchedule,
                    reminderTime = reminderTime,
                    notes = notes
                )
            )
        }
    }

    class Factory(private val repository: HabitRepository) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AddHabitViewModel::class.java)) {
                return AddHabitViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
