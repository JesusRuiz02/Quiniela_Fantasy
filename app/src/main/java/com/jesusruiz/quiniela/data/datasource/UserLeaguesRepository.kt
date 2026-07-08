package com.jesusruiz.quiniela.data.datasource

import com.jesusruiz.quiniela.models.League
import com.jesusruiz.quiniela.utils.Resource
import kotlinx.coroutines.flow.Flow

interface UserLeaguesRepository {
    suspend fun addUserLeague(league: League, userId: String): Resource<Unit>
    suspend fun getUserLeagues(userId: String): Resource<List<League>>
    fun getUserLeaguesFlow(userId: String): Flow<List<League>>
    suspend fun getLeagueById(id: String): Resource<League>
    suspend fun addPointsToUserLeague(leagueId: String, points: Int): Resource<Unit>

}