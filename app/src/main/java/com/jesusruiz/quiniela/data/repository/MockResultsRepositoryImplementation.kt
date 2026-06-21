package com.jesusruiz.quiniela.data.repository

import com.jesusruiz.quiniela.data.datasource.ResultsRepository
import com.jesusruiz.quiniela.models.Game
import com.jesusruiz.quiniela.models.Journey
import com.jesusruiz.quiniela.models.JourneyStates
import com.jesusruiz.quiniela.utils.Resource
import com.jesusruiz.quiniela.utils.generateLeagueId
import kotlinx.coroutines.delay
import javax.inject.Inject

class MockResultsRepositoryImplementation @Inject constructor(): ResultsRepository {

    val leaguesMock: HashMap<String, List<Game>> = hashMapOf(
        "Liga MX" to listOf(
            Game(id = 1, firstClub = "Atlético San Luis", secondClub = "Querétaro", homeScore = 3, awayScore = 0,),
            Game(id = 2, firstClub = "Pachuca", secondClub = "Atlas", homeScore =  3, awayScore = 1,),
            Game(id = 3, firstClub = "Monterrey", secondClub = "León", homeScore = 1, awayScore = 0),
            Game(id = 4, firstClub = "FC Juárez", secondClub = "Necaxa", homeScore = 1, awayScore = 2,),
            Game(id = 5, firstClub = "Chivas", secondClub = "América", homeScore = 1, awayScore = 0,),
            Game(id = 6, firstClub = "Cruz Azul", secondClub = "Tigres", homeScore = 2, awayScore = 1,),
            Game(id = 7, firstClub = "Santos", secondClub = "Mazatlán FC", homeScore = 1, awayScore = 2)
        ),
        "Premier League" to listOf(
            Game(id = 8, firstClub = "Manchester City", secondClub = "Arsenal", homeScore = 0, awayScore = 0),
            Game(id = 9, firstClub = "Liverpool", secondClub = "Brighton", homeScore = 2, awayScore = 1),
            Game(id = 10, firstClub = "Chelsea", secondClub = "Manchester United", homeScore = 4, awayScore = 3),
            Game(id = 11, firstClub = "Newcastle", secondClub = "West Ham", homeScore = 4, awayScore = 3),
            Game(id = 12, firstClub = "Tottenham", secondClub = "Luton Town", homeScore = 2, awayScore = 1),
            Game(id = 13, firstClub = "Aston Villa", secondClub = "Wolves", homeScore = 2, awayScore = 0),
            Game(id = 14, firstClub = "Brentford", secondClub = "Man United", homeScore = 1, awayScore = 1)
        ),
        "Liga Española" to listOf(
            Game(id = 15, firstClub = "Real Madrid", secondClub = "Athletic Club", homeScore = 2, awayScore = 0),
            Game(id = 16, firstClub = "Barcelona", secondClub = "Las Palmas", homeScore = 1, awayScore = 0),
            Game(id = 17, firstClub = "Villarreal", secondClub = "Atlético de Madrid", homeScore = 1, awayScore = 2),
            Game(id = 18, firstClub = "Valencia", secondClub = "Mallorca", homeScore = 0, awayScore = 0),
            Game(id = 19, firstClub = "Girona", secondClub = "Real Betis", homeScore = 3, awayScore = 2),
            Game(id = 20, firstClub = "Real Sociedad", secondClub = "Alavés", homeScore = 1, awayScore = 0),
            Game(id = 21, firstClub = "Celta de Vigo", secondClub = "Rayo Vallecano", homeScore = 0, awayScore = 0)
        )
    )




    val userJourney: Journey = Journey(
        id = "prueba",
        roundNumber = 16,
        results = leaguesMock["Liga MX"]!!,
        API_LEAGUE_ID = generateLeagueId(),
        prediction = leaguesMock["Liga MX"]!!,
        journeyState = JourneyStates.FINISHED,
        score = 10
    )

    val leagues: HashMap<String, String> = hashMapOf(
        "01" to "Liga MX",
        "02" to "Premier League",
        "03" to "Liga Española"
    )

    override suspend fun getResultsByJourney(
        leagueID: String,
        journeyID: String
    ): Resource<List<Game>> {

        try {
            delay(300)
            return Resource.Success(leaguesMock[leagueID]!!)
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

    override suspend fun getGamesByWeekAndLeague(leagueID: String, journeyID: String): Resource<List<Game>> {
        try {
            delay(300)
           val originalGames = leaguesMock[leagueID] ?: listOf()
           val resetedGames = originalGames.map { game ->
               game.copy(homeScore =  0, awayScore =  0)
           }
            return Resource.Success(resetedGames)
        }catch (e: Exception){
            return Resource.Error("Error")
        }
    }

    override suspend fun getPredictionByLeague(leagueID: String): Resource<List<Game>> {
        try {
           val journeyLeague = leaguesMock[leagueID]
           val startPrediction = journeyLeague!!.map {
               game ->
               game.copy(homeScore = 0, awayScore = 0)
           }
            return Resource.Success(startPrediction)
        }
        catch (e: Exception){
            return Resource.Error("Error")
        }
    }


}