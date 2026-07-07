package com.jesusruiz.quiniela.viewmodels

import androidx.lifecycle.ViewModel
import com.jesusruiz.quiniela.data.datasource.UserLeaguesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserLeagueViewModel @Inject constructor(
    private val userLeaguesRepository: UserLeaguesRepository
): ViewModel() {

}