package com.akole.weddingapp.ui.screens.home

import android.content.Context
import android.content.Intent
import android.provider.CalendarContract
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.akole.weddingapp.Constants.CALENDAR_NOTIFICATION_START_TIMESTAMP
import com.akole.weddingapp.R

@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel()){
    Box(modifier = Modifier
        .fillMaxSize()
    ){
        HomeScreenContent(
            viewState = viewModel.state,
            onEventHandler = viewModel::on
        )
    }
    val context = LocalContext.current
    LaunchedEffect(viewModel.oneShotEvents) {
        viewModel.oneShotEvents.collect { event ->
            when (event) {
                HomeViewModel.OneShotEvent.GoToCalendar -> goToCalendar(context)
            }
        }
    }
}

fun goToCalendar(context: Context) {
    // Google Calendar event starts hours before
    val eventStartTime = CALENDAR_NOTIFICATION_START_TIMESTAMP
    val insertCalendarIntent = Intent(Intent.ACTION_INSERT)
        .setData(CalendarContract.Events.CONTENT_URI)
        .putExtra(CalendarContract.Events.TITLE, R.string.calendar_title)
        .putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, false)
        .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, eventStartTime)
        .putExtra(CalendarContract.Events.EVENT_LOCATION, R.string.calendar_place)
        .putExtra(CalendarContract.Events.DESCRIPTION, R.string.calendar_description)
        .putExtra(CalendarContract.Events.ACCESS_LEVEL, CalendarContract.Events.ACCESS_PRIVATE)
        .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_FREE)
    context.startActivity(insertCalendarIntent)
}
