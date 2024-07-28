package com.exfarnanda1945.movietix.core.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.exfarnanda1945.movietix.detail.presentation.DetailScreen
import com.exfarnanda1945.movietix.home.HomeScreen
import kotlinx.serialization.Serializable

@Composable
fun NavigationGraph(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = Graph.Home) {
        composable<Graph.Home> {
            HomeScreen(onDetail = {
                Log.d("onDetail", "NavigationGraph: $it")
                navHostController.navigate(Graph.Detail(it))
            })
        }
        composable<Graph.Detail> {
            val args = it.toRoute<Graph.Detail>()
            DetailScreen(args.id)
        }
    }
}

sealed class Graph {
    @Serializable
    data object Home

    @Serializable
    data class Detail(val id: Int)

}