package com.jesusruiz.quiniela.models

data class  Game(
    var id: Int = 0,
    val homeScore: Int = 0,
    val awayScore: Int = 0,
    val firstClub: String = "",
    val secondClub: String = "",
    val isPrediction: Boolean = false
)
