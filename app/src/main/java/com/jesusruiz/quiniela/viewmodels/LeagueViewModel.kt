package com.jesusruiz.quiniela.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jesusruiz.quiniela.data.datasource.ResultsRepository
import com.jesusruiz.quiniela.models.League
import com.jesusruiz.quiniela.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class CreateLeagueInputActions {
    data class ChangeSelectedLeague(val value: Pair<String, String>): CreateLeagueInputActions()
    data class ChangeNameSelectedLeague(val value: String): CreateLeagueInputActions()
}

data class LeagueStateUI(
    val leaguesAvailable: HashMap<String, String> = hashMapOf(),
    val selectedLeague: League = League()
)

@HiltViewModel
class LeagueViewModel @Inject constructor(
    val repository: ResultsRepository
): ViewModel() {
    private val _leagueState = MutableStateFlow(LeagueStateUI())
    val state: StateFlow<LeagueStateUI> = _leagueState.asStateFlow()

    fun onAction(action: CreateLeagueInputActions){
        when(action){
            is CreateLeagueInputActions.ChangeSelectedLeague -> {
                val (id, leagueName) = action.value
                val league = _leagueState.value.selectedLeague
                _leagueState.value = _leagueState.value.copy(selectedLeague = league.copy(apiLeagueName = leagueName, apiID = id))
            }
            is CreateLeagueInputActions.ChangeNameSelectedLeague -> {
                val league = _leagueState.value.selectedLeague
                _leagueState.value = _leagueState.value.copy(selectedLeague = league.copy(leagueName = action.value))
            }
        }
    }

    fun getAvailableLeagues(){
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getAllApiLeagues()
            when(result){
                is Resource.Error -> {
                    Log.d("Error", result.message)}
                is Resource.Success -> {
                    Log.d("Ligas", result.data.toString())
                    _leagueState.value = _leagueState.value.copy(leaguesAvailable = result.data)
                }
            }
        }

    }

}