package com.jesusruiz.quiniela.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.jesusruiz.quiniela.navigation.Screen

@Composable
fun HomeView(navController: NavController){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text("QUINIELA", modifier = Modifier
            .padding(20.dp)
            , style = MaterialTheme.typography.titleLarge)
        Button(modifier =
            Modifier.fillMaxWidth()
                .padding(horizontal = 20.dp),
            onClick = {
                navController.navigate(Screen.CreationLeagueView.route)

        }, shape = RectangleShape,) {
            Text("Crear liga")
        }

    }
}

@Preview
@Composable
fun HomePreview(){
    HomeView(navController = rememberNavController())
}