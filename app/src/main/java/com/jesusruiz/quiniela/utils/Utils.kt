package com.jesusruiz.quiniela.utils

import com.jesusruiz.quiniela.models.Game
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid


fun Game.toScore(): Int{
  return 0
}

@OptIn(ExperimentalUuidApi::class)
 fun generateLeagueId(): String{
    val randomId = Uuid.random()
    return randomId.toString()
}


