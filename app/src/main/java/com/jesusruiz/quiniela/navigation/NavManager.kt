package com.jesusruiz.quiniela.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jesusruiz.quiniela.viewmodels.HomeViewModel
import com.jesusruiz.quiniela.viewmodels.JourneyViewModel
import com.jesusruiz.quiniela.viewmodels.LeagueViewModel
import com.jesusruiz.quiniela.views.CreationLeagueView
import com.jesusruiz.quiniela.views.HomeView
import com.jesusruiz.quiniela.views.QuinielaView
import dagger.hilt.android.lifecycle.HiltViewModel

sealed class Screen(val route: String){
    data object HomeView: Screen("Home")
    data object CreationLeagueView: Screen("CreateLeague")
    data object QuinielaView: Screen("QuinielaView/{leagueId}/{journeyId}"){
        fun createRoute(leagueId: String, journeyId: String) = "QuinielaView/$leagueId/$journeyId"
    }
}

@Composable
fun NavManager(){
    val controller = rememberNavController()
    NavHost(navController = controller, startDestination = Screen.HomeView.route){
        composable(Screen.HomeView.route) {
            val homeViewModel: HomeViewModel = hiltViewModel()
            HomeView(controller, homeViewModel)
        }
        composable(Screen.CreationLeagueView.route) {
            val leagueViewModel: LeagueViewModel = hiltViewModel()
            CreationLeagueView(controller, leagueViewModel)
        }
        composable(route = Screen.QuinielaView.route,
            arguments = listOf(
                navArgument("leagueId"){ type = NavType.StringType} ,
                navArgument("journeyId"){ type = NavType.StringType}

            )) {
            backStackEntry ->
            val leagueId = backStackEntry.arguments?.getString("leagueId") ?: ""
            val journeyId = backStackEntry.arguments?.getString("journeyId") ?: ""
            val journeyViewModel: JourneyViewModel = hiltViewModel()
            QuinielaView(navController = controller,journeyViewModel = journeyViewModel,
                leagueID = leagueId,
                journeyId = journeyId
                )
        }
    }

}