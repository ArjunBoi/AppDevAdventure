package com.example.jambook

import android.widget.CalendarView
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import java.time.LocalDate

//import com.example.jambook.ReservationData

@Composable
fun ReserveSlotScreen(onBackClick: () -> Unit) {
    // TODO: Add reserve slot screen content
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = onBackClick,
            modifier = Modifier.align(Alignment.Start)
        ) {
            Text("Back")
        }
        AndroidView(
            modifier = Modifier.fillMaxWidth(),
            factory = { context ->
                CalendarView(context).apply {
                    setOnDateChangeListener { _, year, month, dayOfMonth ->
                        selectedDate = LocalDate.of(year, month + 1, dayOfMonth)
                    }
                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            // Store the reservation data
            val reservationDetails = "Reserved slot for $selectedDate"
            ReservationData.addReservation(reservationDetails)
        }) {
            Text("Confirm Reservation")
        }
    }
}