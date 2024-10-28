package com.example.jambook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jambook.ui.theme.JamBookTheme
import androidx.navigation.compose.NavHost
//import androidx.compose.
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JamBookTheme {
                MainScreen()
            }
        }
    }
}

//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                onReserveSlotClick = { navController.navigate("reserveSlot") },
                onViewReservationClick = { navController.navigate("viewReservation") }
            )
        }
        composable("reserveSlot") {
            ReserveSlotScreen(onBackClick = { navController.popBackStack() })
        }
        composable("viewReservation") {
            ViewReservationScreen(onBackClick = { navController.popBackStack() })
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    JamBookTheme {
//        Greeting("Android")
//    }
//}