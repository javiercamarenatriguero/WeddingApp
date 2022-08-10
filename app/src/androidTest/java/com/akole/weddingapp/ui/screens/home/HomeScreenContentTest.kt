package com.akole.weddingapp.ui.screens.home

import android.content.Context
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.akole.weddingapp.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeScreenContentTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun show_initial_values_of_counter_when_loaded(): Unit = with(composeTestRule) {
        setContent {
            HomeScreenContent(
                viewState = MOCK_INITIAL_STATE,
                onEventHandler = {}
            )
        }
        onNodeWithText(MOCK_INITIAL_DAYS_LEFT.toString()).assertExists()
        onNodeWithText(MOCK_INITIAL_HOURS_LEFT.toString()).assertExists()
        onNodeWithText(MOCK_INITIAL_MINUTES_LEFT.toString()).assertExists()
        onNodeWithText(MOCK_INITIAL_SECONDS_LEFT.toString()).assertExists()
    }

    @Test
    fun show_add_calendar_button_and_check_performance(): Unit = with(composeTestRule) {
        var wasCalled = false
        setContent {
            HomeScreenContent(
                viewState = MOCK_INITIAL_STATE,
                onEventHandler = { wasCalled = it == HomeViewModel.ViewEvent.AddCalendarClicked }
            )
        }
        onNodeWithText(context.getString(R.string.add_to_calendar)).assertHasClickAction()
        onNodeWithText(context.getString(R.string.add_to_calendar)).performClick()
        assert(value = wasCalled)
    }

    companion object {
        private const val MOCK_INITIAL_DAYS_LEFT = 1
        private const val MOCK_INITIAL_HOURS_LEFT = 23
        private const val MOCK_INITIAL_MINUTES_LEFT = 12
        private const val MOCK_INITIAL_SECONDS_LEFT = 20

        private val MOCK_INITIAL_STATE = HomeViewModel.UiState(
            daysLeft = MOCK_INITIAL_DAYS_LEFT,
            hoursLeft = MOCK_INITIAL_HOURS_LEFT,
            minutesLeft = MOCK_INITIAL_MINUTES_LEFT,
            secondsLeft = MOCK_INITIAL_SECONDS_LEFT
        )
    }
}