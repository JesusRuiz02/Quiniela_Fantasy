package com.jesusruiz.quiniela.data.repository

import com.jesusruiz.quiniela.data.datasource.UserLeaguesRepository
import com.jesusruiz.quiniela.models.League
import com.jesusruiz.quiniela.utils.Resource

class MockUserLeagueImplementation: UserLeaguesRepository {
    var userLeagues: MutableList<League> = mutableListOf()
    override suspend fun addUserLeague(league: League): Resource<Unit> {
        userLeagues.add(league)
        return Resource.Success(Unit)
    }
}