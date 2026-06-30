package com.example.repository

import com.example.database.dao.StudyDao
import com.example.database.entities.StudySessionEntity
import kotlinx.coroutines.flow.Flow

class StudyRepository(
    private val studyDao: StudyDao
) {
    fun getAllStudySessions(): Flow<List<StudySessionEntity>> = studyDao.getAllStudySessions()

    suspend fun insertStudySession(session: StudySessionEntity) {
        studyDao.insertStudySession(session)
    }
}
