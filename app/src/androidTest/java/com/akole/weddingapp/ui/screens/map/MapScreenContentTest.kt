package com.akole.weddingapp.ui.screens.map

import android.content.Context
import androidx.compose.ui.test.junit4.createComposeRule
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
    }

    companion object {
        private val MOCK_INITIAL_STATE = ViewState()
        private val MOCK_SECOND_ITEM_SELECTED_STATE = ViewState(
            mapItemPosition = 2
        )
        private val markers = listOf(
            Constants.councilMarker,
            Constants.cocktelMarker,
            Constants.restaurantMarker
        )
    }
}