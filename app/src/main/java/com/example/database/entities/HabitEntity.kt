package com.example.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habits")
data class HabitEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val category: String,
    val icon: String,
    val color: String,
    val dailyGoal: Int,
    val repeatSchedule: String = "Daily",
    val reminderTime: String = "",
    val notes: String = "",
    val currentStreak: Int = 0,
    val longestStreak: Int = 0
)
