package com.jesusruiz.quiniela.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.jesusruiz.quiniela.data.local.entities.GameEntity

@Dao
interface GameDao {
    @Insert
    suspend fun insertGame(game: GameEntity)

    @Insert
    suspend fun insertGames(games: List<GameEntity>)

    @Query("SELECT * FROM games WHERE journeyId = :journeyId")
    suspend fun getGamesByJourneyId(journeyId: String): List<GameEntity>

    @Query("SELECT * FROM games WHERE id = :gameId")
    suspend fun getGameById(gameId: String): GameEntity?

    @Update
    suspend fun updateGame(game: GameEntity)
}