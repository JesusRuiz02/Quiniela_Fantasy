package com.jesusruiz.quiniela.views.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jesusruiz.quiniela.R

@Composable
fun UserLeaderboardItem(modifier: Modifier, userName: String, userPoints: Int, onClick: ()-> Unit, ranking: String){
    Row(modifier = modifier.clickable( onClick = { onClick() })
        , verticalAlignment = Alignment.CenterVertically) {
        Text(modifier = modifier.padding(start = 10.dp).weight(0.1f),text = ranking)
        Image(modifier = Modifier.padding(horizontal = 10.dp).weight(0.2f), painter = painterResource(id = R.drawable.ic_soccer), contentDescription = null)
        Text(text = userName, modifier = Modifier.weight(0.5f))
        Text(text = (userPoints.toString() + "PTS"), modifier = Modifier.weight(0.2f))
    }
}

@Preview(showBackground = true)
@Composable
fun UserLeaderPreview(){
    UserLeaderboardItem(modifier = Modifier, userName = "Jesus Ruiz", userPoints = 123, onClick = {}, ranking = "1")
}