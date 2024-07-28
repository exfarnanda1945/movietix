package com.exfarnanda1945.movietix.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.exfarnanda1945.movietix.detail.presentation.DetailScreen
import com.exfarnanda1945.movietix.home.HomeScreen
import com.exfarnanda1945.movietix.search.presentation.SearchFilmScreen
import kotlinx.serialization.Serializable

@Composable
fun NavigationGraph(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = Graph.Home) {
        composable<Graph.Home> {
            HomeScreen(onDetail = {
                navHostController.navigate(Graph.Detail(it))
            }, navigateToSearch = {
                navHostController.navigate(Graph.Search)
            })
        }
        composable<Graph.Detail> {
            val args = it.toRoute<Graph.Detail>()
            DetailScreen(id = args.id, onBack = {
                navHostController.navigateUp()
            })
        }
        composable<Graph.Search> {
            SearchFilmScreen(toDetail = {
                navHostController.navigate(Graph.Detail(it))
            }, onBack = {
                navHostController.navigateUp()
            })
        }
    }
}

sealed class Graph {
    @Serializable
    data object Home

    @Serializable
    data class Detail(val id: Int)

    @Serializable
    data object Search

}