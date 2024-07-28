package com.exfarnanda1945.movietix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.exfarnanda1945.movietix.core.navigation.NavigationGraph
import com.exfarnanda1945.movietix.ui.theme.MovieTixTheme
import org.koin.androidx.compose.KoinAndroidContext
import org.koin.core.annotation.KoinExperimentalAPI

class MainActivity : ComponentActivity() {
    @OptIn(KoinExperimentalAPI::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KoinAndroidContext {
                MovieTixTheme {
                    val navController = rememberNavController()
                   NavigationGraph(navHostController = navController)
                }
            }
        }
    }
}
