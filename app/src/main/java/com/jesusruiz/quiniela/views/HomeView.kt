package com.jesusruiz.quiniela.views

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jesusruiz.quiniela.navigation.Screen
import com.jesusruiz.quiniela.viewmodels.HomeViewModel
import com.jesusruiz.quiniela.views.items.LeagueItem

@Composable
fun HomeView(navController: NavController, homeViewModel: HomeViewModel){
    val state by homeViewModel.state.collectAsState()
    LaunchedEffect(Unit) {
        Log.d("Liga", state.userLeagues.size.toString())
    }
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text("QUINIELA", modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 50.dp)
            , style = MaterialTheme.typography.titleLarge)
        Button(modifier =
            Modifier.fillMaxWidth()
                .padding(horizontal = 20.dp),
            onClick = {
                navController.navigate(Screen.CreationLeagueView.route)
        }, shape = RectangleShape,) {
            Text("Crear liga")
        }
        Card(Modifier.fillMaxWidth().padding(20.dp)) {
           Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth().padding(20.dp),) {
               Text("Mis Ligas")
               Log.d("Liga", state.userLeagues.toString())
               state.userLeagues.forEach {
                   league ->
                   Log.d("Liga", league.id)
                   LeagueItem(modifier = Modifier.padding(10.dp), leagueName = league.leagueName, leagueApiName = league.apiLeagueName) {
                        navController.navigate(Screen.LeagueView.createRoute(leagueId = league.id))
                   }
               }


           }

        }


    }
}

