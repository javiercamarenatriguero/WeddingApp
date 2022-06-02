package com.akole.weddingapp.ui.utils

fun Long.getDaysLeft() = this / 86400000

fun Long.getHoursLeft() = (this / 3600000) % 24

fun Long.getMinutesLeft() = (this / 60000) % 60

fun Long.getSecondsLeft() = (this / 1000) % 60