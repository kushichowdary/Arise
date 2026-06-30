package com.example.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "study_sessions")
data class StudySessionEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val subject: String,
    val durationMinutes: Int,
    val date: String, // format "yyyy-MM-dd"
    val notes: String = ""
)
