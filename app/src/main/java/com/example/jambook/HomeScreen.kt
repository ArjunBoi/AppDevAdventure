package com.example.jambook

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

//@Preview
@Composable
fun HomeScreen(
    onReserveSlotClick: () -> Unit,
    onViewReservationClick: () -> Unit
) {
    val UWColor = 0xFF3700B3
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = onReserveSlotClick,colors = ButtonDefaults.buttonColors(
            containerColor = Color(UWColor)    // Custom hex color
        )) {
            Text("Reserve Slot")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onViewReservationClick,colors = ButtonDefaults.buttonColors(
            containerColor = Color(UWColor)    // Custom hex color
        )) {
            Text("View Reservation")
        }
    }
}

//view other people's bookings
//more granular booking system