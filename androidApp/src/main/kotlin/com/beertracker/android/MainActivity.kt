package com.beertracker.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.beertracker.android.navigation.NavGraph
import com.beertracker.android.ui.theme.BeerTrackerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BeerTrackerTheme {
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}
