package com.jesusruiz.quiniela.models

import android.icu.util.TimeZone

data class League(
    val id: String = "",
    val leagueName: String = "",
    val description: String = "",
    val apiID: String = "",
    val ownerId: String = "",
    val startDate: Long = 0,
    val apiLeagueName: String  = ""
)
data class LeagueStandings(
    val leagueId: String = "",
    val totalPoints: Int = 0,
    val standings: List<JourneyScore> = listOf()
)

data class JourneyScore(
    val journeyId: String = "",
    val score: Int = 0,
    val userId: String = "",
    val leagueId: String = "")


