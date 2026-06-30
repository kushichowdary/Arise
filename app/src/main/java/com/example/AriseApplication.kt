package com.example

import android.app.Application
import com.example.database.AriseDatabase
import com.example.repository.HabitRepository
import com.example.repository.WaterRepository
import com.example.repository.StudyRepository
import com.example.repository.WorkoutRepository
import androidx.room.Room

class AriseApplication : Application() {
    lateinit var database: AriseDatabase
    lateinit var habitRepository: HabitRepository
    lateinit var waterRepository: WaterRepository
    lateinit var studyRepository: StudyRepository
    lateinit var workoutRepository: WorkoutRepository

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            this,
            AriseDatabase::class.java,
            "arise_db"
        ).fallbackToDestructiveMigration().build()
        habitRepository = HabitRepository(database.habitDao())
        waterRepository = WaterRepository(database.waterDao())
        studyRepository = StudyRepository(database.studyDao())
        workoutRepository = WorkoutRepository(database.workoutDao())
    }
}