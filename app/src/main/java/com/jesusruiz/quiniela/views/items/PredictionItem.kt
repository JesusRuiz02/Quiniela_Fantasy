package com.jesusruiz.quiniela.views.items


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jesusruiz.quiniela.R

@Composable
fun PredictionItem(modifier: Modifier = Modifier, localTeam: String = "Cruz Azul", homeScore: Int, awayScore: Int, awayTeam: String = "Monterrey", textColor: Color = Color.White, result: Int = 1){
    var localTeamScore by remember { mutableStateOf(homeScore.toString()) }
    var awayTeamScore by remember { mutableStateOf(awayScore.toString()) }
    var columnColor = Color.LightGray

    columnColor = if (result <= 0 ){
        Color.Red
    } else if(result == 3){
        Color.Green
    } else{
        Color.LightGray
    }

    Row(modifier = modifier
        .padding(horizontal = 10.dp, vertical = 10.dp)
        .background(color = columnColor,shape = RoundedCornerShape(16.dp))
        ,
        verticalAlignment = Alignment.CenterVertically,
    ){
        Column(modifier = modifier.weight(0.33f), horizontalAlignment = Alignment.CenterHorizontally) {
            Image(modifier = Modifier.size(50.dp),
                painter = painterResource(id = R.drawable.ic_soccer), contentDescription = null)
            Text(text = localTeam, color = textColor)
        }
        Row(modifier = modifier.weight(0.33f)
            .padding(horizontal = 10.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(modifier = Modifier.weight(0.33f), contentAlignment = Alignment.Center) {
                Text(modifier = Modifier, text = localTeamScore, color = textColor, textAlign = TextAlign.Center)
            }
            Box(modifier = Modifier.weight(0.33f),  contentAlignment = Alignment.Center) {
                Text(modifier = Modifier, text = "-", color = textColor,textAlign = TextAlign.Center)
            }
            Box(modifier = Modifier.weight(0.33f),  contentAlignment = Alignment.Center) {
                Text(modifier = Modifier, text = awayTeamScore, color = textColor,textAlign = TextAlign.Center)
            }
        }
        Column(modifier = modifier.weight(0.33f), horizontalAlignment = Alignment.CenterHorizontally) {
            Image(modifier = Modifier.size(50.dp),
                painter = painterResource(id = R.drawable.ic_soccer), contentDescription = null)
            Text(text = awayTeam, color = textColor)
        }
    }

}


@Preview(showBackground = true)
@Composable
fun PredictionPreview(){
    PredictionItem(modifier = Modifier, homeScore = 0 , awayScore = 0)
}