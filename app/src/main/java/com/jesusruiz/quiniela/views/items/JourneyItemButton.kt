package com.jesusruiz.quiniela.views.items

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun JourneyItemButton(modifier: Modifier = Modifier, weekNumber: Int = 1, weekPoints: Int = 0){
    var isActive by remember { mutableStateOf(false) }
    Box(modifier = modifier.
    size(50.dp)
        .background(color = Color.DarkGray, shape = CircleShape)
        .clickable( onClick = { isActive = true }
        ),
        contentAlignment = Alignment.Center){
        Column(modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = " J$weekNumber", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleSmall, color = Color.White)
            if (isActive){
            Text(text = weekPoints.toString(), style = MaterialTheme.typography.bodySmall, color = Color.White)
            }

        }
    }
}

@Composable
@Preview
fun JourneyItemButtonPreview(){
    JourneyItemButton(weekNumber = 17, weekPoints = 39)
}