package com.jesusruiz.quiniela.models

data class Quiniela(
    val id: Int = 0,
    val score: Int = 0,
    var predictions: List<Game> = listOf(),
    val user: String = "",
    val league: League,
    val week: Journey
)
