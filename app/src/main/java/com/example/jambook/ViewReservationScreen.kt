package com.example.jambook

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

//import com.example.jambook.ReservationData
import com.example.jambook.Slot
import java.time.LocalDateTime

@Composable
fun ViewReservationScreen(onBackClick: () -> Unit) {
    // Add view reservation screen content
//    val reservationDetails = remember { mutableStateListOf<String>() }
    val reservationDetails = ReservationData.reservations
    val UWColor = 0xFF3700B3


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
            modifier = Modifier.align(Alignment.Start).padding(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(UWColor)    // Custom hex color
            )
        ) {
            Text("Back")
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(reservationDetails) { slot ->
                // Display slot details
                Column {
                    Text("Start: ${slot.startTime}")
                    Text("End: ${slot.endTime}")
                    Text("User: ${slot.userName}")

                    // Cancel Reservation button
                    val confirmDialog = remember { mutableStateOf(false) }
                    Button(
                        onClick = {
                            val currentTime = LocalDateTime.now()
                            if (currentTime.isBefore(slot.startTime.minusHours(1))) {
                                confirmDialog.value = true
                            } else {
                                // Show error message or disable cancel button
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(UWColor)    // Custom hex color
                        )
                    ) {
                        Text("Cancel Reservation")
                    }

                    if (confirmDialog.value) {
                        AlertDialog(
                            onDismissRequest = { confirmDialog.value = false },
                            title = { Text("Confirm Cancellation") },
                            text = { Text("Are you sure you want to cancel this reservation?") },
                            confirmButton = {
                                Button(
                                    onClick = {
                                        ReservationData.removeReservation(slot)
                                        confirmDialog.value = false
                                    },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(UWColor)    // Custom hex color
                                    )
                                ) {
                                    Text("Yes")
                                }
                            },
                            dismissButton = {
                                Button(onClick = { confirmDialog.value = false },colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(UWColor)    // Custom hex color
                                )) {
                                    Text("No")
                                }
                            }
                        )
                    }
                }
            }
        }
//        LazyColumn(
//            modifier = Modifier.fillMaxSize(),
//            contentPadding = PaddingValues(16.dp)
//        ) {
//            items(reservationDetails) { slot ->
//                // Display slot details
//                Row {
//                    Text("Start: ${slot.startTime}")
//                    Text("End: ${slot.endTime}")
//                    Text("User: ${slot.userName}")
//
//                    // Cancel Reservation button
//                    Button(
//                        onClick = {
//                            val currentTime = LocalDateTime.now()
//                            if (currentTime.isBefore(slot.startTime.minusHours(1))) {
//                                // Show confirmation dialog
//                                val confirmDialog = remember { mutableStateOf(false) }
//                                if (confirmDialog.value) {
//                                    AlertDialog(
//                                        onDismissRequest = { confirmDialog.value = false },
//                                        title = { Text("Confirm Cancellation") },
//                                        text = { Text("Are you sure you want to cancel this reservation?") },
//                                        confirmButton = {
//                                            Button(
//                                                onClick = {
//                                                    ReservationData.removeReservation(slot)
//                                                    confirmDialog.value = false
//                                                }
//                                            ) {
//                                                Text("Yes")
//                                            }
//                                        },
//                                        dismissButton = {
//                                            Button(onClick = { confirmDialog.value = false }) {
//                                                Text("No")
//                                            }
//                                        }
//                                    )
//                                }
//                            } else {
//                                // Show error message or disable cancel button
//                            }
//                        }
//                    ) {
//                        Text("Cancel Reservation")
//                    }
//                }
//            }
//        }
//        LazyColumn(
//            modifier = Modifier.fillMaxSize(),
//            contentPadding = PaddingValues(16.dp)
//        ) {
////            items(reservationDetails) { detail ->
////                Text(text = detail)
////            }
//            items(reservationDetails) { slot ->
//                // Display slot details
//                Row {
//                    Text("Start: ${slot.startTime}")
//                    Text("End: ${slot.endTime}")
//                    Text("User: ${slot.userName}")
//
//                    // Cancel Reservation button
//                    Button(
//                        onClick = {
//                            val currentTime = LocalDateTime.now()
//                            if (currentTime.isBefore(slot.startTime.minusHours(1))) {
//                                // Show confirmation dialog
//                                val confirmDialog = remember { mutableStateOf(false) }
//                                if (confirmDialog.value) {
//                                    AlertDialog(
//                                        onDismissRequest = { confirmDialog.value = false },
//                                        title = { Text("Confirm Cancellation") },
//                                        text = { Text("Are you sure you want to cancel this reservation?") },
//                                        confirmButton = {
//                                            Button(
//                                                onClick = {
//                                                    ReservationData.removeReservation(slot)
//                                                    confirmDialog.value = false
//                                                }
//                                            ) {
//                                                Text("Yes")
//                                            }
//                                        },
//                                        dismissButton = {
//                                            Button(onClick = { confirmDialog.value = false }) {
//                                                Text("No")
//                                            }
//                                        }
//                                    )
//                                }
//
//                                // Show cancel button
//                                Button(onClick = { confirmDialog.value = true }) {
//                                    Text("Cancel Reservation")
//                                }
//                            } else {
//                                // Show error message or disable cancel button
//                            }
//                        },
//                        content = TODO()
////                        modifier = TODO(),
////                        enabled = TODO(),
////                        shape = TODO(),
////                        colors = TODO(),
////                        elevation = TODO(),
////                        border = TODO(),
////                        contentPadding = TODO(),
////                        interactionSource = TODO(),
////                        content = TODO()
//                    )
//                }
//            }
//        }
    }
}