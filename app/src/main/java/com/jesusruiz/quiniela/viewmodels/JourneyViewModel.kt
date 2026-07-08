package com.jesusruiz.quiniela.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jesusruiz.quiniela.data.datasource.ResultsRepository
import com.jesusruiz.quiniela.data.datasource.UserLeaguesRepository
import com.jesusruiz.quiniela.data.repository.MockCalculationPointsImp
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

enum class UIStates{
    LOADING,
    FAILED,
    READY,
    STARTING
}

data class JourneyState(
    val scores: List<Game> = listOf(),
    val journeySelected: Journey = Journey(API_LEAGUE_ID = "0"),
    val score: Int = 0,
    val predictions: List<Game> = listOf(),
    val uiState: UIStates = UIStates.STARTING,
    val gameScore: List<Int> = listOf()
)

sealed class JourneyInputActions{
    data class ChangePrediction(val value: Game): JourneyInputActions()
    data class InitScreen(val leagueId: String, val journeyId: String): JourneyInputActions()
    object ChangeAllPredictions : JourneyInputActions()
    data class ChangeUIState(val value: UIStates): JourneyInputActions()
    data class ChangeScore(val value: Int): JourneyInputActions()
    data class ChangeGameScore(val value: List<Int>): JourneyInputActions()
}
val journeys = TemplateData().journey
@HiltViewModel
class JourneyViewModel @Inject constructor(
    private val repository: ResultsRepository,
    private val leaguesRepository: UserLeaguesRepository,
    private val mathRepository: MockCalculationPointsImp
): ViewModel(){
    val TAG = this::class.simpleName

    private val _journeyState = MutableStateFlow(JourneyState())
        val state: StateFlow<JourneyState> = _journeyState.asStateFlow()

    fun getJourneyLeague(){
        if (state.value.journeySelected.API_LEAGUE_ID.isEmpty())
        {
            viewModelScope.launch {
             val userLeague =  leaguesRepository.getUserLeagues("12")

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
            is JourneyInputActions.ChangeGameScore -> {
                Log.d( TAG, "Game scores updated: ${action.value}")
                _journeyState.update { jState ->
                    jState.copy(gameScore = action.value)
                }
            }
            is JourneyInputActions.ChangeScore -> {
                _journeyState.update { jState ->
                    jState.copy(score = action.value)
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
                    Log.d( TAG, predictions.message)
                }
            }

        }
    }


    fun getPredictionRanking(){
        viewModelScope.launch {
            onAction(JourneyInputActions.ChangeUIState(UIStates.LOADING))
            val results = getResultsByRepository()
            results?.let { games ->
                _journeyState.update  { it.copy(scores = games) }
                val newScore = getPredictionPoints()
                Log.d( TAG, newScore.toString())
                onAction(JourneyInputActions.ChangeUIState(UIStates.READY))
            } ?: run {
                onAction(JourneyInputActions.ChangeUIState(UIStates.FAILED))
                Log.d( TAG, "No funciona")
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
                      Log.d( TAG, result.message)
                      null
                  }
              }
          }

        }
    }

     fun startPredictionPointsCalculation() {
        viewModelScope.launch {
            getPredictionPoints()
        }
    }

    fun startPredictionPointsByBetCalculation() {
        viewModelScope.launch {
            getPredictionsByBet()
        }
    }

    suspend fun getPredictionsByBet() {
       return withContext(Dispatchers.IO) {
           val results = getResultsByRepository()
           _journeyState.update { currentState ->
               currentState.copy(scores = results ?: listOf())
           }
           val result = mathRepository.getPredictionsPointsByBet(
                prediction = _journeyState.value.predictions,
                results = _journeyState.value.scores
              )
              when(result) {
                is Resource.Success -> {
                    onAction(JourneyInputActions.ChangeGameScore(result.data))
                }
                is Resource.Error -> {
                    Log.d( TAG, result.message)
                }
              }
           }
    }

   suspend fun getPredictionPoints() {
     return withContext(Dispatchers.IO) {
           val result = mathRepository.getPredictionsPoints(
                prediction = _journeyState.value.predictions,
                results = _journeyState.value.scores
              )
              when(result){
                is Resource.Success ->
                    onAction(JourneyInputActions.ChangeScore( result.data))
                is Resource.Error -> {
                     Log.d( TAG, result.message)
                }
              }
         }

    }


}