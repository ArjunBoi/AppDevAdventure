package com.example.jambook

import java.time.LocalDateTime

data class Slot(
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
    val userName: String
)
