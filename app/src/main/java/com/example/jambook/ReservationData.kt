package com.example.jambook

import androidx.compose.runtime.mutableStateListOf

object ReservationData {
    private val _reservations = mutableStateListOf<String>()
    val reservations: List<String> = _reservations

    fun addReservation(reservation: String) {
        _reservations.add(reservation)
    }
}