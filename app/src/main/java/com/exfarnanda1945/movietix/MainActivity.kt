package com.exfarnanda1945.movietix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.exfarnanda1945.movietix.home.HomeScreen
import com.exfarnanda1945.movietix.ui.theme.MovieTixTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieTixTheme {
                HomeScreen()
            }
        }
    }
}
