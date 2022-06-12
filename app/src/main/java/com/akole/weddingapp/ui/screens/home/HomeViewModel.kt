package com.akole.weddingapp.ui.screens.home

import android.os.CountDownTimer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akole.weddingapp.Constants.WEDDING_TIMESTAMP
import com.akole.weddingapp.ui.utils.getDaysLeft
import com.akole.weddingapp.ui.utils.getHoursLeft
import com.akole.weddingapp.ui.utils.getMinutesLeft
import com.akole.weddingapp.ui.utils.getSecondsLeft
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.util.Date

class HomeViewModel: ViewModel() {

    var state by mutableStateOf(UiState())
        private set

    private val _oneShotEvents = Channel<OneShotEvent>(Channel.BUFFERED)
    val oneShotEvents: Flow<OneShotEvent> = _oneShotEvents.receiveAsFlow()

    init {
        viewModelScope.launch {
            val currentTimestamp = Date().time
            val timestampToDate = WEDDING_TIMESTAMP - currentTimestamp
            if (timestampToDate <= 0) {
                updateState(isWeddingTime = true)
            } else {
                updateState(
                    daysLeft = timestampToDate.getDaysLeft().toInt(),
                    hoursLeft = timestampToDate.getHoursLeft().toInt(),
                    minutesLeft = timestampToDate.getMinutesLeft().toInt(),
                    secondsLeft = timestampToDate.getSecondsLeft().toInt(),
                    isWeddingTime = false,
                )
            }
            object : CountDownTimer(timestampToDate, 1000) {

                override fun onTick(millisUntilFinished: Long) {
                    updateState(
                        daysLeft = millisUntilFinished.getDaysLeft().toInt(),
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

    fun on(event: ViewEvent): Unit = with(event) {
        when (this) {
            ViewEvent.AddCalendarClicked -> onAddCalendarClicked()
        }
    }

    private fun onAddCalendarClicked() {
        emit(OneShotEvent.GoToCalendar)
    }

    private fun emit(event: OneShotEvent) {
        viewModelScope.launch {
            _oneShotEvents.send(event)
        }
    }

    sealed interface OneShotEvent {
        object GoToCalendar : OneShotEvent
    }

    sealed interface ViewEvent {
        object AddCalendarClicked: ViewEvent
    }

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
}