package com.jesusruiz.quiniela.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RewriteQueriesToDropUnusedColumns
import androidx.room.Update
import com.jesusruiz.quiniela.data.local.entities.LeagueEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LeagueDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLeague(league: LeagueEntity)

    @Insert
    suspend fun insertLeagues(leagues: List<LeagueEntity>)

    @Update
    suspend fun updateLeague(league: LeagueEntity)

    @Query("SELECT * FROM leagues WHERE id = :leagueId")
    suspend fun getLeagueById(leagueId: String): LeagueEntity

    @Query("SELECT * FROM leagues WHERE name = :leagueName")
    suspend fun getLeagueByName(leagueName: String): LeagueEntity?

    @Query("""
        SELECT l.* FROM leagues l
        JOIN league_members lm ON l.id = lm.leagueId
        WHERE lm.userId = :userId
    """)
    suspend fun getLeaguesByUserId(userId: String): List<LeagueEntity>

    @RewriteQueriesToDropUnusedColumns
    @Query("SELECT * FROM leagues l JOIN league_members lm ON l.id=lm.leagueId WHERE lm.userId=:userId")
    fun getLeaguesByUserIdFlow(userId: String): Flow<List<LeagueEntity>>

}

