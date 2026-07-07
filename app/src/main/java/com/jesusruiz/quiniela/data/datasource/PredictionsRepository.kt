package com.jesusruiz.quiniela.data.datasource

import android.gesture.Prediction
import com.jesusruiz.quiniela.models.Game
import com.jesusruiz.quiniela.utils.Resource

interface PredictionsRepository {

    suspend fun getPredictionsPointsByBet(prediction: List<Game>, results: List<Game>): Resource<List<Int>>

    suspend fun getPredictionsPoints(prediction: List<Game>, results: List<Game>): Resource<Int>

}