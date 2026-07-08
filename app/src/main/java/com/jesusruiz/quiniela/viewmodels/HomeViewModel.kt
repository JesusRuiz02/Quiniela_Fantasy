package com.jesusruiz.quiniela.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jesusruiz.quiniela.data.datasource.ResultsRepository
import com.jesusruiz.quiniela.data.datasource.UserLeaguesRepository
import com.jesusruiz.quiniela.models.League
import com.jesusruiz.quiniela.models.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeState(
    val userLeagues: List<League> = listOf()
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    val repository: ResultsRepository,
    val userLeaguesRepository: UserLeaguesRepository
): ViewModel() {
    private val userLeagueRepository = userLeaguesRepository
    private val TAG = this::class.simpleName
    private val _homeState = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _homeState.asStateFlow()

    val user: User = User(
        id = "12",
        username = "Dummy User",
        email = "dummyuser@example.com"
    )

    init {
        subscribeToLeagues()
    }
    private fun subscribeToLeagues(){
        Log.d(TAG, "Subscribing to leagues for user: ${user.id}")
        userLeagueRepository.getUserLeaguesFlow(user.id)
            .onEach {
                leagues ->
                _homeState.update { it.copy(userLeagues = leagues) }
            }
            .launchIn(viewModelScope)
        Log.d(TAG, "Subscribed to leagues for user: ${_homeState.value.userLeagues}")
    }

    fun getLeagues(){
        viewModelScope.launch(Dispatchers.IO) {
            val leagues = userLeagueRepository.getUserLeagues(user.id)
        }
    }

}