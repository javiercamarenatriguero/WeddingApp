package com.akole.weddingapp.ui.utils

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun Long.getDaysLeft() = this / 86400000

fun Long.getHoursLeft() = (this / 3600000) % 24

fun Long.getMinutesLeft() = (this / 60000) % 60

fun Long.getSecondsLeft() = (this / 1000) % 60

fun Long.formatTimestamp(format: String): String =
    convertToLocalDate(this)?.format(DateTimeFormatter.ofPattern(format)) ?: ""

private fun convertToLocalDate(epochTimestamp: Long): LocalDateTime? =
    Instant.ofEpochMilli(epochTimestamp)
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime()

