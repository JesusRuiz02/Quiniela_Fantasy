package com.jesusruiz.quiniela

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.jesusruiz.quiniela.ui.theme.QuinielaTheme
import com.jesusruiz.quiniela.viewmodels.JourneyViewModel
import com.jesusruiz.quiniela.views.QuinielaView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val journeyViewModel: JourneyViewModel = hiltViewModel()
            QuinielaTheme {
               QuinielaView(modifier = Modifier, journeyViewModel)
            }
        }
    }
}



