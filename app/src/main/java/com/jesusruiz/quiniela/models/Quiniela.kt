package com.jesusruiz.quiniela.models

data class QuinielaLeague(
    val id: Int = 0,
    val users: List<String>,
    val apiLeague: League,
    val currentWeek: String
)
