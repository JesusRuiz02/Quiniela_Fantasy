package com.jesusruiz.quiniela.data.repository

import android.util.Log
import androidx.room.withTransaction
import com.jesusruiz.quiniela.data.datasource.UserLeaguesRepository
import com.jesusruiz.quiniela.data.local.QuinielaDatabase
import com.jesusruiz.quiniela.data.local.daos.GameDao
import com.jesusruiz.quiniela.data.local.daos.LeagueDao
import com.jesusruiz.quiniela.data.local.daos.LeagueMembersDao
import com.jesusruiz.quiniela.data.local.daos.PredictionDao
import com.jesusruiz.quiniela.data.local.daos.UserDao
import com.jesusruiz.quiniela.data.local.entities.LeagueMemberEntity
import com.jesusruiz.quiniela.data.local.entities.UserEntity
import com.jesusruiz.quiniela.models.League
import com.jesusruiz.quiniela.models.User
import com.jesusruiz.quiniela.utils.Resource
import com.jesusruiz.quiniela.utils.generateLeagueId
import com.jesusruiz.quiniela.utils.toLeague
import com.jesusruiz.quiniela.utils.toLeagueEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class MockUserLeagueImplementation @Inject constructor(
    private val db: QuinielaDatabase,
    private val leagueDao: LeagueDao,
    private val memberDao: LeagueMembersDao,
    private val userDao: UserDao
): UserLeaguesRepository {
   private var userLeagues = MutableStateFlow<List<League>>(emptyList())
    override suspend fun addUserLeague(league: League, userId: String): Resource<Unit> {
       return try {
            val id = league.id.ifBlank { generateLeagueId() }
            val leagueWithId = league.copy(id = id, ownerId = userId, startDate = System.currentTimeMillis(), apiLeagueName = league.apiLeagueName, apiID = league.apiID)
           Log.d("DB_DEBUG","addUserLeague start id=$id user=$userId")
            db.withTransaction {
                if (userDao.getUserById(userId) == null) {
                    userDao.insertUser(UserEntity(id = userId, username = "", email = ""))
                }
                leagueDao.insertLeague(leagueWithId.toLeagueEntity())
                memberDao.insertLeagueMember(LeagueMemberEntity(userId = userId, leagueId = id, joinedDate = System.currentTimeMillis()))
            }
           Log.d("DB_DEBUG","addUserLeague committed id=$id")
             Resource.Success(Unit)
        }catch (e: Exception) {
             Resource.Error("Failed to add user league: ${e.message}")
        }
    }

    override suspend fun getUserLeagues(userId: String): Resource<List<League>> {
        return try {
            val leagues = leagueDao.getLeaguesByUserId(userId).map {
                    entity -> entity.toLeague()
            }
            return Resource.Success(leagues)
        } catch (e: Exception) {
            Resource.Error("Failed to fetch user leagues: ${e.message}")
        }


    }

    override fun getUserLeaguesFlow(userId: String): Flow<List<League>> = leagueDao.getLeaguesByUserIdFlow(userId).map { list -> list.map { it.toLeague() } }
    override suspend fun getLeagueById(id: String): Resource<League> {
        return leagueDao.getLeagueById(id)?.let { leagueEntity ->
            val league = leagueEntity.toLeague()
            Resource.Success(league)
        } ?: Resource.Error(message = "League not found")
    }

    override suspend fun addPointsToUserLeague(
        leagueId: String,
        points: Int
    ): Resource<Unit> {
       return Resource.Success(Unit)
    }

}