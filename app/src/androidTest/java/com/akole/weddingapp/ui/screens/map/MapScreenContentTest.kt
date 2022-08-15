package com.akole.weddingapp.ui.screens.map

import android.content.Context
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.akole.weddingapp.Constants
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MapScreenContentTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun show_initial_values_of_map_when_loaded(): Unit = with(composeTestRule) {
        setContent {
            MapScreenContent(
                viewState = MOCK_INITIAL_STATE,
                markers = markers,
                onViewEvent = {}
            )
        }
        // First item (visible)
        onNodeWithText(context.getString(markers.first().details)).assertIsDisplayed()
        onNodeWithText(context.getString(markers.first().title)).assertIsDisplayed()
        // Last item (hidden)
        onNodeWithText(context.getString(markers.last().details)).assertDoesNotExist()
        onNodeWithText(context.getString(markers.last().title)).assertDoesNotExist()
    }

    @Test
    fun show_last_item_when_item_position_is_the_latest_item(): Unit = with(composeTestRule) {
        setContent {
            MapScreenContent(
                viewState = MOCK_INITIAL_STATE.copy(
                    mapItemPosition = markers.size - 1
                ),
                markers = markers,
                onViewEvent = {}
            )
        }
        // First item (hidden)
        onNodeWithText(context.getString(markers.first().details)).assertDoesNotExist()
        onNodeWithText(context.getString(markers.first().title)).assertDoesNotExist()
        // Last item (visible)
        onNodeWithText(context.getString(markers.last().details)).assertIsDisplayed()
        onNodeWithText(context.getString(markers.last().title)).assertIsDisplayed()
    }

    companion object {
        private val MOCK_INITIAL_STATE = ViewState()
        private val markers = listOf(
            Constants.councilMarker,
            Constants.cocktelMarker,
            Constants.restaurantMarker
        )
    }
}