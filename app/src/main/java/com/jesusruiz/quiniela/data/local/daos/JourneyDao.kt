package com.jesusruiz.quiniela.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.jesusruiz.quiniela.data.local.entities.JourneyEntity
import com.jesusruiz.quiniela.models.Journey

@Dao
interface JourneyDao {

    @Insert
    suspend fun insertJourney(journey: JourneyEntity)

    @Insert
    suspend fun insertJourneys(journeys: List<JourneyEntity>)

    @Query("SELECT * FROM journeys WHERE id = :journeyId and leagueId = :leagueId")
    suspend fun getJourneyById(journeyId: String, leagueId: String): JourneyEntity

    @Update
    suspend fun updateJourney(journey: JourneyEntity)



}