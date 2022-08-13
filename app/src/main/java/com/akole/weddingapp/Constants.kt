package com.akole.weddingapp

import com.akole.weddingapp.ui.screens.map.LocationUIItem
import com.google.android.gms.maps.model.LatLng

object Constants {
    // Random date
    internal const val WEDDING_TIMESTAMP = 1708367000000
    // 12 hours before wedding in order to start calendar notification
    internal const val CALENDAR_NOTIFICATION_START_TIMESTAMP = WEDDING_TIMESTAMP - 7200000
    // Photo service available timestamp (default 0 in order to be available)
    internal const val PHOTO_AVAILABLE_TIMESTAMP = 0
    // Wedding date string format
    internal const val DATETIME_FORMAT = "HH'h'mm - dd.MM.YYYY"
    // Map Locations
    // TODO: Inject by DI
    internal val councilMarker = LocationUIItem(
        position = LatLng(43.2640404644175, -2.9231387425674176),
        title = R.string.council_title,
        snippet = R.string.council_snippet,
        image = R.drawable.marker1,
        details = R.string.council_details,
    )
    internal val cocktelMarker = LocationUIItem(
        position = LatLng(43.263880486320836, -2.9260678485581844),
        title = R.string.cocktel_title,
        snippet = R.string.cocktel_snippet,
        image = R.drawable.marker2,
        details = R.string.cocktel_details,

        )
    internal val restaurantMarker = LocationUIItem(
        position = LatLng(43.29085987514852, -2.925274743006676),
        title = R.string.restaurant_title,
        snippet = R.string.restaurant_snippet,
        image = R.drawable.marker3,
        details = R.string.restaurant_details,
    )
}