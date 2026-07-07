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
            Game(id = 1, homeTeam = "Atlético San Luis", awayTeam = "Querétaro", homeScore = 3, awayScore = 0,),
            Game(id = 2, homeTeam = "Pachuca", awayTeam = "Atlas", homeScore =  3, awayScore = 1,),
            Game(id = 3, homeTeam = "Monterrey", awayTeam = "León", homeScore = 1, awayScore = 0),
            Game(id = 4, homeTeam = "FC Juárez", awayTeam = "Necaxa", homeScore = 1, awayScore = 2,),
            Game(id = 5, homeTeam = "Chivas", awayTeam = "América", homeScore = 1, awayScore = 0,),
            Game(id = 6, homeTeam = "Cruz Azul", awayTeam = "Tigres", homeScore = 2, awayScore = 1,),
            Game(id = 7, homeTeam = "Santos", awayTeam = "Mazatlán FC", homeScore = 1, awayScore = 2)
        ),
        "Premier League" to listOf(
            Game(id = 8, homeTeam = "Manchester City", awayTeam = "Arsenal", homeScore = 0, awayScore = 0),
            Game(id = 9, homeTeam = "Liverpool", awayTeam = "Brighton", homeScore = 2, awayScore = 1),
            Game(id = 10, homeTeam = "Chelsea", awayTeam = "Manchester United", homeScore = 4, awayScore = 3),
            Game(id = 11, homeTeam = "Newcastle", awayTeam = "West Ham", homeScore = 4, awayScore = 3),
            Game(id = 12, homeTeam = "Tottenham", awayTeam = "Luton Town", homeScore = 2, awayScore = 1),
            Game(id = 13, homeTeam = "Aston Villa", awayTeam = "Wolves", homeScore = 2, awayScore = 0),
            Game(id = 14, homeTeam = "Brentford", awayTeam = "Man United", homeScore = 1, awayScore = 1)
        ),
        "Liga Española" to listOf(
            Game(id = 15, homeTeam = "Real Madrid", awayTeam = "Athletic Club", homeScore = 2, awayScore = 0),
            Game(id = 16, homeTeam = "Barcelona", awayTeam = "Las Palmas", homeScore = 1, awayScore = 0),
            Game(id = 17, homeTeam = "Villarreal", awayTeam = "Atlético de Madrid", homeScore = 1, awayScore = 2),
            Game(id = 18, homeTeam = "Valencia", awayTeam = "Mallorca", homeScore = 0, awayScore = 0),
            Game(id = 19, homeTeam = "Girona", awayTeam = "Real Betis", homeScore = 3, awayScore = 2),
            Game(id = 20, homeTeam = "Real Sociedad", awayTeam = "Alavés", homeScore = 1, awayScore = 0),
            Game(id = 21, homeTeam = "Celta de Vigo", awayTeam = "Rayo Vallecano", homeScore = 0, awayScore = 0)
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