package com.jesusruiz.quiniela.data.datasource

import com.jesusruiz.quiniela.models.Game
import com.jesusruiz.quiniela.models.Journey
import com.jesusruiz.quiniela.models.League
import com.jesusruiz.quiniela.utils.Resource

interface ResultsRepository {
    suspend fun getResultsByJourney(leagueID: String, journeyID: String): Resource<List<Game>>
    suspend fun getJourneysByLeague(leagueID: String): Resource<List<Journey>>
    suspend fun getFullJourney(round: Int): Resource<Journey>

    suspend fun getAllApiLeagues(): Resource<HashMap<String, String>>
}