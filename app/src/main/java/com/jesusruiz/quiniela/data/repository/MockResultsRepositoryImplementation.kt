package com.jesusruiz.quiniela.data.repository

import com.jesusruiz.quiniela.data.datasource.ResultsRepository
import com.jesusruiz.quiniela.models.Game
import com.jesusruiz.quiniela.models.Journey
import com.jesusruiz.quiniela.models.JourneyStates
import com.jesusruiz.quiniela.models.League
import com.jesusruiz.quiniela.utils.Resource
import kotlinx.coroutines.delay
import javax.inject.Inject

class MockResultsRepositoryImplementation @Inject constructor(): ResultsRepository {
    val journey: List<Game> = listOf(
        Game(
            id = 1,
            firstClub = "Atlético San Luis",
            secondClub = "Querétaro",
            homeScore = 3,
            awayScore = 0,
        ),
        Game(
            id = 2,
            firstClub = "Pachuca",
            secondClub = "Atlas",
            homeScore =  3,
            awayScore = 1,
        ),
        Game(
            id = 3,
            firstClub = "Monterrey",
            secondClub = "León",
            homeScore = 1,
            awayScore = 0,
        ),
        Game(
            id = 4,
            firstClub = "FC Juárez",
            secondClub = "Necaxa",
            homeScore = 1,
            awayScore = 2,
        ),
        Game(
            id = 5,
            firstClub = "Chivas",
            secondClub = "América",
            homeScore = 1,
            awayScore = 0,
        ),
        Game(
            id = 6,
            firstClub = "Cruz Azul",
            secondClub = "Tigres",
            homeScore = 2,
            awayScore = 1,
        ),
        Game(
            id = 7,
            firstClub = "Santos",
            secondClub = "Mazatlán FC",
            homeScore = 1,
            awayScore = 2,
        )
    )

    val userJourney: Journey = Journey(
        id = "prueba",
        roundNumber = 16,
        results = journey,
        league = League(),
        prediction = journey,
        journeyState = JourneyStates.FINISHED,
        score = 10
    )

    val leagues: HashMap<String, String> = hashMapOf(
        "LigaMx" to "01",
        "PremierLeague" to "02"
    )

    override suspend fun getResultsByJourney(
        leagueID: String,
        journeyID: String
    ): Resource<List<Game>> {

        try {
            delay(300)
            return Resource.Success(journey)
        }catch (e: Exception){
            return Resource.Error("Error")
        }

    }

    override suspend fun getJourneysByLeague(leagueID: String): Resource<List<Journey>> {
        return Resource.Error("no hay info suficiente")
    }

    override suspend fun getFullJourney(round: Int): Resource<Journey> {
        try {
            delay(1500)
            return Resource.Success(userJourney)
        }catch (e : Exception){
            return Resource.Error("Error")
        }
    }

    override suspend fun getAllApiLeagues(): Resource<HashMap<String, String>> {
        try {
            delay(1000)
            return Resource.Success(leagues)
        }
        catch (e: Exception){
            return Resource.Error("Error")
        }
    }


}