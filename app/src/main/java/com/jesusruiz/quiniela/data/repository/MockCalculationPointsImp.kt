package com.jesusruiz.quiniela.data.repository

import android.util.Log
import com.jesusruiz.quiniela.data.datasource.PredictionsRepository
import com.jesusruiz.quiniela.models.Game
import com.jesusruiz.quiniela.utils.Resource
import javax.inject.Inject
import kotlin.math.sign

class MockCalculationPointsImp @Inject constructor(): PredictionsRepository{
    private val TAG = this::class.simpleName
    override suspend fun getPredictionsPointsByBet(
        prediction: List<Game>,
        results: List<Game>
    ): Resource<List<Int>> {
        val newList: MutableList<Int> = mutableListOf()
        Log.d(TAG, results.toString())
        results.forEachIndexed{ index, score ->
            val prediction =  prediction[index]
            val score = when {
                score.homeScore == prediction.homeScore &&
                        score.awayScore == prediction.awayScore -> 3

                (score.homeScore - score.awayScore).sign ==
                        (prediction.homeScore - prediction.awayScore).sign -> 1

                else -> 0
            }
           Log.d(TAG, "getPredictionsPointsByBet: score: $score")
           newList.add(score)
        }
        return Resource.Success(newList)
    }

    override suspend fun getPredictionsPoints(
        prediction: List<Game>,
        results: List<Game>
    ): Resource<Int> {
        var generalPoints = 0
        results.forEachIndexed { index, score ->
            val prediction =  prediction[index]
            generalPoints += when{
                score.homeScore == prediction.homeScore &&
                        score.awayScore == prediction.awayScore -> 3

                (score.homeScore - score.awayScore).sign ==
                        (prediction.homeScore - prediction.awayScore).sign -> 1

                else -> 0
            }
        }
        return Resource.Success(generalPoints)
    }
}