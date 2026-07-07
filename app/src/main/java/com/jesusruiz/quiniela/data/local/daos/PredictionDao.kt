package com.jesusruiz.quiniela.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.jesusruiz.quiniela.data.local.entities.PredictionEntity

@Dao
interface PredictionDao {
    @Insert
    suspend fun insertPrediction(prediction: PredictionEntity)

    @Insert
    suspend fun insertPredictions(predictions: List<PredictionEntity>)

    @Query("""
        SELECT * FROM predictions 
        WHERE userId = :userId AND gameId IN (
            SELECT id FROM games WHERE journeyId = :journeyId
        )
    """)
    suspend fun getUserPredictionsForJourney(
        userId: String,
        journeyId: String
    ): List<PredictionEntity>

    @Query("SELECT * FROM predictions WHERE userId = :userId")
    suspend fun getUserPredictions(userId: String): List<PredictionEntity>


    @Update
    suspend fun updatePrediction(prediction: PredictionEntity)


}