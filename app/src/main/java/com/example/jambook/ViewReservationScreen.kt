package com.example.jambook

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

//import com.example.jambook.ReservationData

@Composable
fun ViewReservationScreen(onBackClick: () -> Unit) {
    // Add view reservation screen content
//    val reservationDetails = remember { mutableStateListOf<String>() }
    val reservationDetails = ReservationData.reservations

//    LazyColumn(
//        modifier = Modifier.fillMaxSize(),
//        contentPadding = PaddingValues(16.dp)
//    ) {
//        items(reservationDetails) { detail ->
//            Text(text = detail)
//        }
//    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Button(
            onClick = onBackClick,
            modifier = Modifier.align(Alignment.Start).padding(16.dp)
        ) {
            Text("Back")
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(reservationDetails) { detail ->
                Text(text = detail)
            }
        }
    }
}