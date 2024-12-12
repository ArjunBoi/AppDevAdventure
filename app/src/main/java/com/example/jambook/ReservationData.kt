package com.example.jambook

import androidx.compose.runtime.mutableStateListOf
import java.time.LocalDateTime

object ReservationData {
    private val _reservations = mutableStateListOf<Slot>()
    val reservations: List<Slot> = _reservations

//    fun addReservation(reservation: String) {
//        _reservations.add(reservation)
//    }
    fun addReservation(slot: Slot) {
        _reservations.add(slot)
    }

    fun removeReservation(slot: Slot) {
        _reservations.remove(slot)
    }

    fun isSlotAvailable(startTime: LocalDateTime, endTime: LocalDateTime): Boolean {
        return _reservations.none { slot ->
            (startTime >= slot.startTime && startTime < slot.endTime) ||
                    (endTime > slot.startTime && endTime <= slot.endTime) ||
                    (startTime <= slot.startTime && endTime >= slot.endTime)
        }
    }
}