package com.jesusruiz.quiniela.views.items

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun LeagueItem(modifier: Modifier,leagueName: String, leagueApiName: String, onClick: ()-> Unit){
    Row(modifier = modifier.fillMaxWidth().padding().clickable( onClick = { onClick()}),
        verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly) {
        Text(leagueName, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Text(leagueApiName, style = MaterialTheme.typography.titleMedium)
    }
}


@Preview
@Composable
fun LeagueItemPreview(){
    LeagueItem(modifier = Modifier, leagueName = "Liga de Prueba", leagueApiName = "Liga mx", onClick = { })
}