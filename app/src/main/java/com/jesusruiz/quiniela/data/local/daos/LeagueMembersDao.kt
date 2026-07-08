package com.jesusruiz.quiniela.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jesusruiz.quiniela.data.local.entities.LeagueMemberEntity

@Dao
interface LeagueMembersDao {
    @Insert
    suspend fun insertLeagueMembers(members: List<LeagueMemberEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertLeagueMember(member: LeagueMemberEntity)

    @Query("SELECT * FROM league_members WHERE leagueId = :leagueId")
    suspend fun getMembersByLeagueId(leagueId: String): List<LeagueMemberEntity>

    @Query("SELECT * FROM league_members WHERE userId = :userId")
    suspend fun getLeaguesByUserId(userId: String): List<LeagueMemberEntity>

    @Query("SELECT * FROM league_members WHERE leagueId = :leagueId AND userId = :userId")
    suspend fun getLeagueMember(leagueId: String, userId: String): LeagueMemberEntity?

}