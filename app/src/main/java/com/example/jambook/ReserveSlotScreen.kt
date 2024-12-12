package com.example.jambook

import android.app.TimePickerDialog
import android.widget.CalendarView
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MenuItemColors
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import java.time.LocalDateTime

//import com.example.jambook.ReservationData

@Composable
fun ReserveSlotScreen(onBackClick: () -> Unit) {

    val snackbarHostState = remember { SnackbarHostState() }
    var selectedDate by rememberSaveable { mutableStateOf(LocalDate.now()) }
    var selectedStartTime by rememberSaveable { mutableStateOf(LocalTime.of(12, 0)) }
    var selectedDuration by rememberSaveable { mutableStateOf(1) }
    val context = LocalContext.current
    val UWColor = 0xFF3700B3

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = onBackClick,
            modifier = Modifier.align(Alignment.Start),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(UWColor)    // Custom hex color
            )
        ) {
            Text("Back")
        }

        //Calemdar View
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

        //Time Slot Selection
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Start time picker
            Button(
                onClick = {
                    val timePickerDialog = TimePickerDialog(
                        context,
                        { _, hour, minute ->
                            selectedStartTime = LocalTime.of(hour, minute)
                        },
                        selectedStartTime.hour,
                        selectedStartTime.minute,
                        false
                    )
                    timePickerDialog.show()
                },
                modifier = Modifier.padding(start = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(UWColor)    // Custom hex color
                )
            ) {
                Text("Select Start Time")
            }

            // Duration picker
            Box(
                modifier = Modifier.padding(end = 16.dp)
            ) {
                var expanded by remember { mutableStateOf(false) }
                val items = listOf("1 hour", "2 hours")
                val selectedItem = items[selectedDuration - 1]

                Text(
                    text = selectedItem,
                    modifier = Modifier
                        .clickable(onClick = { expanded = true })
                        .padding(8.dp)
                )

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    items.forEachIndexed { index, item ->
                        DropdownMenuItem(
                            text = {
                                Text(text = item)
                            },
                            onClick = {
                                selectedDuration = index + 1
                                expanded = false
                            }
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

//        val context = LocalContext.current
//        val scope = rememberCoroutineScope()

        // Confirm Reservation button
        Button(
            onClick = {
                val startDateTime = LocalDateTime.of(selectedDate, selectedStartTime)
                val endDateTime = startDateTime.plusHours(selectedDuration.toLong())
                val currentUser = "Arjun T." // Placeholder

                if (ReservationData.isSlotAvailable(startDateTime, endDateTime)) {
                    val slot = Slot(startDateTime, endDateTime, currentUser)
                    ReservationData.addReservation(slot)

                    // Show confirmation Snackbar
                    val snackbarMessage = "Reservation confirmed for $startDateTime - $endDateTime"
//                    val snackbarAction = "OK"
//                    Toast.makeText(context, snackbarMessage, Toast.LENGTH_SHORT).show()


                    Toast.makeText(context, snackbarMessage, Toast.LENGTH_SHORT).show()
//                    scope.launch {
//                        snackbarHostState.showSnackbar(
//                            message = snackbarMessage,
//                            actionLabel = snackbarAction
//                        )
//                    }
                } else {
                    // Show error message or handle unavailable slot
                    val errorMessage = "Selected slot is not available. Please choose a different slot."
                    Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(UWColor)    // Custom hex color
            )
        ) {
            Text("Confirm Reservation")
        }

    }
}