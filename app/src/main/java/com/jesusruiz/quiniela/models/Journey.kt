package com.jesusruiz.quiniela.models

import com.jesusruiz.quiniela.viewmodels.JourneyState

data class Journey(
    val id: String = "",
    val roundNumber: Int = 0,
    val results: List<Game> = listOf(),
    val prediction: List<Game> = listOf(),
    val league: League,
    val journeyState: JourneyStates = JourneyStates.WAITING,
    val score: Int = 0
)

enum class JourneyStates{
    FINISHED,
    ONGOING,
    WAITING
}
