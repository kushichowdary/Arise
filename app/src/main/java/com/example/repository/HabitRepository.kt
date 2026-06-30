package com.example.repository

import com.example.database.dao.HabitDao
import com.example.database.entities.HabitEntity
import kotlinx.coroutines.flow.Flow

class HabitRepository(
    private val habitDao: HabitDao
) {
    fun getAllHabits(): Flow<List<HabitEntity>> = habitDao.getAllHabits()

    suspend fun insertHabit(habit: HabitEntity) {
        habitDao.insertHabit(habit)
    }

    suspend fun deleteHabit(habit: HabitEntity) {
        habitDao.deleteHabit(habit)
    }
}
