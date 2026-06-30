package com.example.ui.study

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.database.entities.StudySessionEntity
import com.example.repository.StudyRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class StudyTrackerViewModel(
    private val repository: StudyRepository
) : ViewModel() {

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    val sessions: StateFlow<List<StudySessionEntity>> = repository.getAllStudySessions()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addStudySession(subject: String, durationMinutes: Int, notes: String) {
        viewModelScope.launch {
            repository.insertStudySession(
                StudySessionEntity(
                    subject = subject,
                    durationMinutes = durationMinutes,
                    date = dateFormat.format(Date()),
                    notes = notes
                )
            )
        }
    }

    class Factory(private val repository: StudyRepository) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(StudyTrackerViewModel::class.java)) {
                return StudyTrackerViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
