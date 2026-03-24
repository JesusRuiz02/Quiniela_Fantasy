package com.jesusruiz.quiniela.views.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jesusruiz.quiniela.R

@Composable
fun GameItem(modifier: Modifier = Modifier, localTeam: String = "Cruz Azul", homeScore: Int, awayScore: Int, awayTeam: String = "Monterrey", onScoreChange: (Int, Int) -> Unit, textColor: Color = Color.White){
    var localTeamScore by remember { mutableStateOf(homeScore.toString()) }
    var awayTeamScore by remember { mutableStateOf(awayScore.toString()) }
    val focusManager = LocalFocusManager.current
    Row(modifier = modifier
        .padding(horizontal = 10.dp, vertical = 10.dp)
        .background(color = Color.DarkGray,shape = RoundedCornerShape(16.dp))
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
            Box(modifier = Modifier.weight(0.5f)){
                OutlinedTextField(modifier = Modifier,
                    value = localTeamScore,
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Number
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Next)
                        }),
                    colors = OutlinedTextFieldDefaults.colors(focusedTextColor = textColor, unfocusedTextColor =  textColor),
                    onValueChange = { newValue -> if(newValue.all { it.isDigit() } && newValue.length <= 3){
                        localTeamScore = newValue
                        val localInt = newValue.toIntOrNull() ?: 0
                        val awayInt = awayTeamScore.toIntOrNull() ?: 0
                        onScoreChange(localInt, awayInt)
                    }
                    } ,
                )
            }
            Box(modifier = Modifier.weight(0.5f)){
                OutlinedTextField(modifier = Modifier,
                    value = awayTeamScore,
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Number
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                        }),
                    colors = OutlinedTextFieldDefaults.colors(focusedTextColor = textColor, unfocusedTextColor =  textColor),
                    onValueChange = { newValue -> if(newValue.all { it.isDigit() } && newValue.length <= 3){
                        awayTeamScore = newValue
                        val localInt = localTeamScore.toIntOrNull() ?: 0
                        val awayInt = newValue.toIntOrNull() ?: 0
                        onScoreChange(localInt, awayInt)
                    }
                    } ,
                )
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
fun QuinielaPreview(){
    GameItem(modifier = Modifier, homeScore = 0 , awayScore = 0, onScoreChange = {local, away ->})
}