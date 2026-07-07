package com.jesusruiz.quiniela.utils

import com.jesusruiz.quiniela.models.Game

data class TemplateData(val journey: List<Game> = listOf(
    Game(
        id = 1,
        homeTeam = "Atlético San Luis",
        awayTeam = "Querétaro",
        homeScore = 3,
        awayScore = 0,
    ),
    Game(
        id = 2,
        homeTeam = "Pachuca",
        awayTeam = "Atlas",
        homeScore =  3,
        awayScore = 0,
    ),
    Game(
        id = 3,
        homeTeam = "Monterrey",
        awayTeam = "León",
        homeScore = 3,
        awayScore = 0,
    ),
    Game(
        id = 4,
        homeTeam = "FC Juárez",
        awayTeam = "Necaxa",
        homeScore = 0,
        awayScore = 3,
    ),
    Game(
        id = 5,
        homeTeam = "Chivas",
        awayTeam = "América",
        homeScore = 0,
        awayScore = 3,
    ),
    Game(
        id = 6,
        homeTeam = "Cruz Azul",
        awayTeam = "Tigres",
        homeScore = 2,
        awayScore = 1,
    ),
    Game(
        id = 7,
        homeTeam = "Santos",
        awayTeam = "Mazatlán FC",
        homeScore = 0,
        awayScore = 0,
    )
)
)
const val COMPETITION_YEAR = 2026
