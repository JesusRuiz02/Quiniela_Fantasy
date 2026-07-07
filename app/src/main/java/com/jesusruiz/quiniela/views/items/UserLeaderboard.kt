package com.jesusruiz.quiniela.views.items

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jesusruiz.quiniela.models.User

@Composable
fun UserLeaderboard(modifier: Modifier = Modifier, players: List<User>, onClick: (User) -> Unit = {

}) {
    Box(modifier = modifier) {
        LazyColumn(modifier = Modifier) {
            items(players.size) { index ->
                val player = players[index]
                UserLeaderboardItem(modifier = Modifier, userName = player.username, userPoints = 0, onClick = { onClick(player) }, ranking = (index + 1).toString())
            }
        }
    }

}

@Preview
@Composable
fun UserLeaderboardPreview() {
    val list: List<User>  = listOf(User(username = "Player 1"), User(username = "Player 2"))
    UserLeaderboard(modifier = Modifier, list)
}
