package com.jesusruiz.quiniela.views

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jesusruiz.quiniela.viewmodels.CreateLeagueInputActions
import com.jesusruiz.quiniela.viewmodels.LeagueViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreationLeagueView(navController: NavController, leagueViewModel: LeagueViewModel){
    val state by leagueViewModel.state.collectAsState()
    LaunchedEffect(Unit) {
        leagueViewModel.getAvailableLeagues()
    }
    Scaffold(modifier = Modifier, topBar = {
        TopAppBar(title = {
            ""
        }, navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack()
            }) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "")
            }
        })
    }) {
        paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            var expanded by remember { mutableStateOf(false) }
            Text(text = "League Settings", style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(horizontal = 10.dp), fontWeight = FontWeight.Bold)
            OutlinedTextField(modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp, vertical = 15.dp),
                value = state.selectedLeague.leagueName,
                shape = RoundedCornerShape(12.dp),
                onValueChange = { leagueViewModel.onAction(CreateLeagueInputActions.ChangeNameSelectedLeague(it))},
                label = {Text("League Name")}, )
            ExposedDropdownMenuBox(expanded = expanded,
                onExpandedChange = { expanded = !expanded}) {
                OutlinedTextField(
                    label =  { "League"},
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier.fillMaxWidth().padding(20.dp).menuAnchor(
                        ExposedDropdownMenuAnchorType.PrimaryNotEditable),
                    value = state.selectedLeague.apiLeagueName,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                    }
                )
                ExposedDropdownMenu(expanded = expanded, onDismissRequest = {expanded = false}) {
                    state.leaguesAvailable.forEach {
                        (id, name) ->
                        Log.d("Item", name)
                        DropdownMenuItem(text = { Text(text = name) },
                            onClick = {
                                leagueViewModel.onAction(CreateLeagueInputActions.ChangeSelectedLeague(id to name))
                                expanded = false
                            }
                        )
                    }
                }
            }
            Button(onClick = {
                leagueViewModel.addLeague()
                navController.popBackStack()
                }) {
                Text("Crear")
            }
        }

    }

}






