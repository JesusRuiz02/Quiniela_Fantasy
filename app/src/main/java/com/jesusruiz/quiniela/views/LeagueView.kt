package com.jesusruiz.quiniela.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jesusruiz.quiniela.models.User
import com.jesusruiz.quiniela.navigation.Screen
import com.jesusruiz.quiniela.views.items.JourneyItemButton
import com.jesusruiz.quiniela.views.items.UserLeaderboard

@Composable
fun LeagueView(modifier: Modifier = Modifier, navController: NavController? = null, leagueId: String? = null) {
    Column(modifier = modifier.padding(top = 50.dp)) {
        LazyRow(modifier) {
            val list = listOf(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17)
            items(list.size){
                JourneyItemButton(modifier = Modifier.padding(horizontal = 2.dp),weekNumber = list[it], weekPoints = 0)
            }
        }
        val list: List<User>  = listOf(User(username = "Player 1", id = "1"), User(username = "Player 2", id = "2"))
        UserLeaderboard(modifier = Modifier.padding(vertical = 10.dp), players = list){
            navController?.navigate(Screen.QuinielaView.createRoute(leagueId = leagueId ?: "", journeyId = "1"))
        }
    }

}


@Preview(showBackground = true)
@Composable
fun LeagueViewPreview() {
    LeagueView()
}