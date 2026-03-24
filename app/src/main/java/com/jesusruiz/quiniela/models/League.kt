package com.jesusruiz.quiniela.models

data class League(
    val id: String = "",
    val members: List<String> = listOf(),
    val weeks: List<String> = listOf(),
    val leagueName: String = "",
    val apiID: String = "",
    val apiLeagueName: String  = ""
)
