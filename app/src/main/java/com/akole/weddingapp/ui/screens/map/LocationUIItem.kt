package com.akole.weddingapp.ui.screens.map

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.google.android.gms.maps.model.LatLng

data class LocationUIItem(
    val position: LatLng,
    @StringRes val title: Int,
    @StringRes val snippet: Int,
    @DrawableRes val icon: Int = 0,
    @DrawableRes val image: Int = 0,
    @StringRes val details: Int = 0
)