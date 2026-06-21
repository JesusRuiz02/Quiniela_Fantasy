package com.jesusruiz.quiniela.models


data class Journey(
    val id: String = "",
    val roundNumber: Int = 0,
    val results: List<Game> = listOf(),
    val prediction: List<Game> = listOf(),
    val API_LEAGUE_ID: String,
    val journeyState: JourneyStates = JourneyStates.WAITING,
    val score: Int = 0
)

enum class JourneyStates{
    FINISHED,
    ONGOING,
    WAITING
}
