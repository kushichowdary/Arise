package com.example.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.database.dao.HabitDao
import com.example.database.dao.WaterDao
import com.example.database.dao.StudyDao
import com.example.database.dao.WorkoutDao
import com.example.database.entities.HabitEntity
import com.example.database.entities.WaterEntity
import com.example.database.entities.StudySessionEntity
import com.example.database.entities.WorkoutEntity

@Database(entities = [
    HabitEntity::class, 
    WaterEntity::class,
    StudySessionEntity::class,
    WorkoutEntity::class
], version = 3, exportSchema = false)
abstract class AriseDatabase : RoomDatabase() {
    abstract fun habitDao(): HabitDao
    abstract fun waterDao(): WaterDao
    abstract fun studyDao(): StudyDao
    abstract fun workoutDao(): WorkoutDao
}
