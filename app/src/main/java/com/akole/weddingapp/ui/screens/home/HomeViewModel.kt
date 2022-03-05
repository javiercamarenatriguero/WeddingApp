package com.akole.weddingapp.ui.screens.home

import android.os.CountDownTimer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.time.Instant

class HomeViewModel: ViewModel() {

    var state by mutableStateOf(UiState())
        private set

    init {
        viewModelScope.launch {
            val currentTimestamp = Instant.now().toEpochMilli()
            val timestampToDate = WEDDING_TIMESTAMP - currentTimestamp
            if (timestampToDate <= 0) {
                updateState(isWeddingTime = true)
            } else {
                updateState(
                    daysLeft = timestampToDate.getDaysLetf().toInt(),
                    hoursLeft = timestampToDate.getHoursLeft().toInt(),
                    minutesLeft = timestampToDate.getMinutesLeft().toInt(),
                    secondsLeft = timestampToDate.getSecondsLeft().toInt(),
                    isWeddingTime = false,
                )
            }
            object : CountDownTimer(timestampToDate, 1000) {

                override fun onTick(millisUntilFinished: Long) {
                    updateState(
                        daysLeft = millisUntilFinished.getDaysLetf().toInt(),
                        hoursLeft = millisUntilFinished.getHoursLeft().toInt(),
                        minutesLeft = millisUntilFinished.getMinutesLeft().toInt(),
                        secondsLeft = millisUntilFinished.getSecondsLeft().toInt(),
                        isWeddingTime = false,
                    )
                }
                override fun onFinish() {
                    updateState(
                        daysLeft = 0,
                        hoursLeft = 0,
                        minutesLeft = 0,
                        secondsLeft = 0,
                        isWeddingTime = true
                    )
                }
            }.start()
        }
    }

    private fun Long.getDaysLetf() = this / 86400000

    private fun Long.getHoursLeft() = (this / 3600000) % 24

    private fun Long.getMinutesLeft() = (this / 60000) % 60

    private fun Long.getSecondsLeft() = (this / 1000) % 60

    data class UiState(
        val daysLeft: Int = 0,
        val hoursLeft: Int = 0,
        val minutesLeft: Int = 0,
        val secondsLeft: Int = 0,
        val isWeddingTime: Boolean = false
    )

    private fun updateState(
        daysLeft: Int = state.daysLeft,
        hoursLeft: Int = state.hoursLeft,
        minutesLeft: Int = state.minutesLeft,
        secondsLeft: Int = state.secondsLeft,
        isWeddingTime: Boolean = state.isWeddingTime
    ) {
        state = UiState(
            daysLeft,
            hoursLeft,
            minutesLeft,
            secondsLeft,
            isWeddingTime
        )
    }

    companion object {
        internal const val WEDDING_TIMESTAMP = 1653672600000
    }
}