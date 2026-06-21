package com.jesusruiz.quiniela.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jesusruiz.quiniela.data.datasource.ResultsRepository
import com.jesusruiz.quiniela.data.datasource.UserLeaguesRepository
import com.jesusruiz.quiniela.models.Game
import com.jesusruiz.quiniela.models.Journey
import com.jesusruiz.quiniela.utils.TemplateData
import com.jesusruiz.quiniela.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.math.sign

enum class UIStates{
    LOADING,
    FAILED,
    READY,
    STARTING
}

data class JourneyState(
    val journeys: List<Journey> = listOf(),
    val scores: List<Game> = listOf(),
    val journeySelected: Journey = Journey(API_LEAGUE_ID = "0"),
    val score: Int = 0,
    val predictions: List<Game> = listOf(),
    val uiState: UIStates = UIStates.STARTING,
)

sealed class JourneyInputActions{
    data class ChangePrediction(val value: Game): JourneyInputActions()
    data class InitScreen(val leagueId: String, val journeyId: String): JourneyInputActions()
    object ChangeAllPredictions : JourneyInputActions()
    data class ChangeUIState(val value: UIStates): JourneyInputActions()
}
val journeys = TemplateData().journey
@HiltViewModel
class JourneyViewModel @Inject constructor(
    private val repository: ResultsRepository,
    private val leaguesRepository: UserLeaguesRepository
): ViewModel(){
    private val _journeyState = MutableStateFlow(JourneyState())
        val state: StateFlow<JourneyState> = _journeyState.asStateFlow()

    fun getJourneyLeague(){
        if (state.value.journeySelected.API_LEAGUE_ID.isEmpty())
        {
            viewModelScope.launch {
             val userLeague =  leaguesRepository.getUserLeagues()

            }

        }
    }
    fun onAction(action: JourneyInputActions){
        when(action){
            is JourneyInputActions.ChangePrediction -> {
                _journeyState.update { currentState ->
                    val updatedPrediction = _journeyState.value.predictions.map {
                        if(it.id == action.value.id) {
                            it.copy(homeScore = action.value.homeScore, awayScore = action.value.awayScore)
                        } else {
                            it
                        }
                    }
                    currentState.copy(predictions = updatedPrediction )
                }

            }
            is JourneyInputActions.ChangeAllPredictions -> {
                getGamesByWeekAndJourney()
            }
            is JourneyInputActions.ChangeUIState -> {
                _journeyState.value = _journeyState.value.copy(uiState = action.value)
            }
            is JourneyInputActions.InitScreen ->{
                val newJourney = _journeyState.value.journeySelected.copy(
                    id = action.journeyId,
                    API_LEAGUE_ID = action.leagueId)
                _journeyState.value = _journeyState.value.copy(journeySelected = newJourney)
            }


        }

    }

    fun getGamesByWeekAndJourney(){
        viewModelScope.launch {
           // onAction(JourneyInputActions.ChangeUIState(UIStates.LOADING))
            val journeyS = _journeyState.value.journeySelected
            val predictions = repository.getGamesByWeekAndLeague( leagueID = journeyS.API_LEAGUE_ID, journeyID = journeyS.id)
            when(predictions){
                is Resource.Success -> {
                    _journeyState.value =  _journeyState.value.copy(predictions = predictions.data)
                }
                is Resource.Error -> {
                    Log.d("Error", predictions.message)
                }
            }

        }
    }


    fun getPredictionRanking(){
        viewModelScope.launch {
            onAction(JourneyInputActions.ChangeUIState(UIStates.LOADING))
            val results = getResultsByRepository()
            results?.let { games ->
                _journeyState.value = _journeyState.value.copy(scores = games)
                val newScore = getPredictionPoints()
                _journeyState.value = _journeyState.value.copy(score =  newScore)
                Log.d("Score", newScore.toString())
                onAction(JourneyInputActions.ChangeUIState(UIStates.READY))
            } ?: run {
                onAction(JourneyInputActions.ChangeUIState(UIStates.FAILED))
                Log.d("Score", "No funciona")
            }
        }
    }

    fun getJourneyByID(ID: String){
        onAction(JourneyInputActions.ChangeUIState(UIStates.LOADING))
        val actualJourney = _journeyState.value.journeys.find {
           it.id == ID
       }
        actualJourney?.let {
            _journeyState.value = _journeyState.value.copy(journeySelected = actualJourney)
        } ?: run {
            viewModelScope.launch(Dispatchers.IO) {
                //repository //Get UserJourney In My DataBase
              val result = repository.getFullJourney(1)
                when(result){
                    is Resource.Success ->{
                       _journeyState.value = _journeyState.value.copy(journeySelected = result.data)
                    }
                    is Resource.Error -> {
                        result.message
                    }
                }
            }
        }
    }


    suspend fun getResultsByRepository(): List<Game>?{
        return withContext(Dispatchers.IO) {
          val leagueId = _journeyState.value.journeySelected.API_LEAGUE_ID
          leagueId.let {
              val result = repository.getResultsByJourney(leagueId,"0")
              when(result){
                  is Resource.Success->{
                      result.data
                  }
                  is Resource.Error->{
                      Log.d("Error", result.message)
                      null
                  }
              }
          }

        }
    }

    fun getPredictionsByBet(): List<Int>{
        val newList: MutableList<Int> = mutableListOf()
        _journeyState.value.scores.forEachIndexed{ index, score ->
            val prediction =  _journeyState.value.predictions[index]
           val score = when{
                score.homeScore == prediction.homeScore &&
                        score.awayScore == prediction.awayScore -> 3

                (score.homeScore - score.awayScore).sign ==
                        (prediction.homeScore - prediction.awayScore).sign -> 1

                else -> 0
            }
            newList.add(score)
        }
        return newList
    }

    fun getPredictionPoints(): Int{
        var generalPoints = 0
         _journeyState.value.scores.forEachIndexed{ index, score ->
            val prediction =  _journeyState.value.predictions[index]
            generalPoints += when{
                score.homeScore == prediction.homeScore &&
                        score.awayScore == prediction.awayScore -> 3

                (score.homeScore - score.awayScore).sign ==
                        (prediction.homeScore - prediction.awayScore).sign -> 1

                else -> 0
            }
        }

        return generalPoints

    }


}