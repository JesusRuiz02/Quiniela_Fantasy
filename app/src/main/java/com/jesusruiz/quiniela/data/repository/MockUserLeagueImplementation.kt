package com.jesusruiz.quiniela.data.repository

import android.R
import com.jesusruiz.quiniela.data.datasource.UserLeaguesRepository
import com.jesusruiz.quiniela.models.League
import com.jesusruiz.quiniela.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class MockUserLeagueImplementation @Inject constructor(): UserLeaguesRepository {
   private var userLeagues = MutableStateFlow<List<League>>(emptyList())
    override suspend fun addUserLeague(league: League): Resource<Unit> {
        userLeagues.update {
            currentList -> currentList + league
        }
        return Resource.Success(Unit)
    }

    override suspend fun getUserLeagues(): Resource<List<League>> {
       return Resource.Success(userLeagues.value)
    }


    override fun getUserLeaguesFlow(): Flow<List<League>> = userLeagues.asStateFlow()
    override fun getLeagueById(id: String): Resource<League> {
        for (league in userLeagues.value) {
            if (league.id == id )
            {
                return Resource.Success(league)
            }

        }
        return Resource.Error(message = "League not found")
    }

}