package com.akole.weddingapp

import androidx.annotation.DrawableRes
import com.akole.weddingapp.ui.screens.map.body.LocationUIItem
import com.google.android.gms.maps.model.LatLng

internal object Constants {
    // Random date
    const val WEDDING_TIMESTAMP = 1708367000000
    // 12 hours before wedding in order to start calendar notification
    const val CALENDAR_NOTIFICATION_START_TIMESTAMP = WEDDING_TIMESTAMP - 7200000
    // Photo service available timestamp (default 0 in order to be available)
    const val PHOTO_AVAILABLE_TIMESTAMP = 0
    // Wedding date string format
    const val DATETIME_FORMAT = "HH'h'mm - dd.MM.YYYY"
    // Wedding's main characters :)
    // Name
    const val CHARACTER_NAME_1 = "Pablo"
    const val CHARACTER_NAME_2 = "Rosa"
    // Images
    @DrawableRes const val CHARACTER_IMAGE_1 = R.drawable.profile1
    @DrawableRes const val CHARACTER_IMAGE_2 = R.drawable.profile2
    // Map Locations
    // TODO: Inject by DI
    val councilMarker = LocationUIItem(
        position = LatLng(43.2640404644175, -2.9231387425674176),
        title = R.string.council_title,
        snippet = R.string.council_snippet,
        image = R.drawable.marker1,
        details = R.string.council_details
    )
    val cocktailMarker = LocationUIItem(
        position = LatLng(43.263880486320836, -2.9260678485581844),
        title = R.string.cocktel_title,
        snippet = R.string.cocktel_snippet,
        image = R.drawable.marker2,
        details = R.string.cocktel_details
    )
    val restaurantMarker = LocationUIItem(
        position = LatLng(43.29085987514852, -2.925274743006676),
        title = R.string.restaurant_title,
        snippet = R.string.restaurant_snippet,
        image = R.drawable.marker3,
        details = R.string.restaurant_details
    )
}