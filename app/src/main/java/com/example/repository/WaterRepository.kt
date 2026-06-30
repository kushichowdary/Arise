package com.example.repository

import com.example.database.dao.WaterDao
import com.example.database.entities.WaterEntity
import kotlinx.coroutines.flow.Flow

class WaterRepository(
    private val waterDao: WaterDao
) {
    fun getWaterByDate(date: String): Flow<WaterEntity?> = waterDao.getWaterByDate(date)

    suspend fun insertWater(water: WaterEntity) {
        waterDao.insertWater(water)
    }

    suspend fun updateWater(water: WaterEntity) {
        waterDao.updateWater(water)
    }

    suspend fun addWater(date: String, amount: Int, defaultGoal: Int = 2000) {
        val current = waterDao.getWaterByDateSync(date)
        if (current != null) {
            waterDao.updateWater(current.copy(consumedAmount = current.consumedAmount + amount))
        } else {
            waterDao.insertWater(WaterEntity(date = date, consumedAmount = amount, goalAmount = defaultGoal))
        }
    }
}
