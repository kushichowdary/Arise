package com.example.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "water")
data class WaterEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: String, // format: "yyyy-MM-dd"
    val goalAmount: Int = 2000, // ml
    val consumedAmount: Int = 0
)
