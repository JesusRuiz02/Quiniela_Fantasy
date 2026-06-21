package com.jesusruiz.quiniela.data.datasource

import com.jesusruiz.quiniela.models.League
import com.jesusruiz.quiniela.utils.Resource
import kotlinx.coroutines.flow.Flow

interface UserLeaguesRepository {
    suspend fun addUserLeague(league: League): Resource<Unit>
    suspend fun getUserLeagues(): Resource<List<League>>
    fun getUserLeaguesFlow(): Flow<List<League>>
    fun getLeagueById(id: String): Resource<League>

}