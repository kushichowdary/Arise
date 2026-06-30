package com.example.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.database.entities.WaterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WaterDao {
    @Query("SELECT * FROM water WHERE date = :date LIMIT 1")
    fun getWaterByDate(date: String): Flow<WaterEntity?>

    @Query("SELECT * FROM water WHERE date = :date LIMIT 1")
    suspend fun getWaterByDateSync(date: String): WaterEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWater(water: WaterEntity)

    @Update
    suspend fun updateWater(water: WaterEntity)
}
