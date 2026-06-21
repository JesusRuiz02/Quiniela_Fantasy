package com.jesusruiz.quiniela.utils

import com.jesusruiz.quiniela.models.Game

data class TemplateData(val journey: List<Game> = listOf(
    Game(
        id = 1,
        firstClub = "Atlético San Luis",
        secondClub = "Querétaro",
        homeScore = 3,
        awayScore = 0,
    ),
    Game(
        id = 2,
        firstClub = "Pachuca",
        secondClub = "Atlas",
        homeScore =  3,
        awayScore = 0,
    ),
    Game(
        id = 3,
        firstClub = "Monterrey",
        secondClub = "León",
        homeScore = 3,
        awayScore = 0,
    ),
    Game(
        id = 4,
        firstClub = "FC Juárez",
        secondClub = "Necaxa",
        homeScore = 0,
        awayScore = 3,
    ),
    Game(
        id = 5,
        firstClub = "Chivas",
        secondClub = "América",
        homeScore = 0,
        awayScore = 3,
    ),
    Game(
        id = 6,
        firstClub = "Cruz Azul",
        secondClub = "Tigres",
        homeScore = 2,
        awayScore = 1,
    ),
    Game(
        id = 7,
        firstClub = "Santos",
        secondClub = "Mazatlán FC",
        homeScore = 0,
        awayScore = 0,
    )
)
)
const val COMPETITION_YEAR = 2026
