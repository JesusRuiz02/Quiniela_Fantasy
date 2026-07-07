package com.jesusruiz.quiniela.models

data class Prediction(
    val id: String = "",
    val userId: String = "",
    val gameId: String = "",
    val predictedHomeScore: Int = 0,
    val predictedAwayScore: Int = 0,
    val pointEarned: Int = 0
)