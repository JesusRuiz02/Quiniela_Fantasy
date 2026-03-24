package com.jesusruiz.quiniela.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jesusruiz.quiniela.viewmodels.JourneyInputActions
import com.jesusruiz.quiniela.viewmodels.JourneyViewModel
import com.jesusruiz.quiniela.viewmodels.UIStates
import com.jesusruiz.quiniela.views.items.GameItem
import com.jesusruiz.quiniela.views.items.JourneyItemButton


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuinielaView(modifier: Modifier = Modifier, journeyViewModel: JourneyViewModel){
    val state by journeyViewModel.state.collectAsState()
    LaunchedEffect(Unit) {
        journeyViewModel.onAction(JourneyInputActions.ChangeAllPredictions)
    }

    Scaffold(modifier = Modifier, topBar ={
        CenterAlignedTopAppBar(
            title = {
                Text(text = "Jornada de hoy", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.secondary)
            },
            navigationIcon = {
                TextButton (onClick = {

                }){
                    Text("Go home")
                }
            }
        )
    }) {

        paddingValues ->
        if (state.uiState == UIStates.LOADING){
            Box( modifier = Modifier.fillMaxSize().padding(paddingValues)) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
        else{
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(paddingValues)) {
                LazyRow(modifier = Modifier.padding(horizontal = 10.dp)) {
                    items(items = state.journeys){
                        journey ->
                        JourneyItemButton(Modifier.padding(horizontal = 3.dp),weekNumber = journey.roundNumber, weekPoints = state.score,)
                    }
                }
                LazyColumn(modifier = Modifier.padding(vertical = 10.dp)) {
                    items(state.predictions, key = {it.id}){
                            game->
                        GameItem(localTeam = game.firstClub, awayTeam = game.secondClub, homeScore = game.homeScore, awayScore = game.awayScore , onScoreChange = {
                            local, away ->
                            val updatedScore = game.copy(
                                homeScore = local,
                                awayScore = away
                            )
                            journeyViewModel.onAction(JourneyInputActions.ChangePrediction(updatedScore))
                        })
                    }
                }
                Button(onClick = {
                    journeyViewModel.getPredictionRanking()
                },) {
                    Text("Validar")
                }
            }
        }
        }

}


@Preview(showBackground = true)
@Composable
fun HomeViewPreview(){
}