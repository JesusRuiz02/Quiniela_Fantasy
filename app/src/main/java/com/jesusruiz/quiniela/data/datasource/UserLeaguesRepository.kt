package com.jesusruiz.quiniela.data.datasource

import com.jesusruiz.quiniela.models.League
import com.jesusruiz.quiniela.utils.Resource

interface UserLeaguesRepository {
    suspend fun addUserLeague(league: League): Resource<Unit>

}