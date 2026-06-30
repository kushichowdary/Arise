package com.example.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workouts")
data class WorkoutEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val type: String, // e.g. "Running", "Gym"
    val durationMinutes: Int,
    val calories: Int,
    val date: String,
    val notes: String = ""
)
