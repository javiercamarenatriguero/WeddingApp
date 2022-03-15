package com.akole.weddingapp.ui.screens.map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun WeddingMap(
    mapPosition: Int,
    locationItems: List<LocationItem>,
    modifier: Modifier = Modifier,
    onPositionChanged: (Int) -> Unit ={}
) {
    var isMapLoaded by remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()

    val properties by remember {
        mutableStateOf(
            MapProperties(
                mapType = MapType.HYBRID
            )
        )
    }
    val uiSettings by remember {
        mutableStateOf(
            MapUiSettings(
                zoomGesturesEnabled = true,
                zoomControlsEnabled = false
            )
        )
    }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(locationItems[mapPosition].position, 13f)
    }

    GoogleMap(
        modifier = modifier
            .fillMaxSize(),
        properties = properties,
        uiSettings = uiSettings,
        cameraPositionState = cameraPositionState,
        onMapLoaded = {
            coroutineScope.launch {
                delay(0)
                cameraPositionState.animate(
                    CameraUpdateFactory
                        .newLatLngZoom(locationItems[mapPosition].position, 18f)
                )
                isMapLoaded = true
            }
        }
    ){
        locationItems.forEach { locationItem ->
            Marker(
                position = locationItem.position,
                title = stringResource(id = locationItem.title),
                snippet = stringResource(id = locationItem.snippet),
                //icon = BitmapDescriptorFactory.fromResource(locationItem.icon),
                visible = true
            )
        }
    }
    LaunchedEffect(mapPosition) {
        if(isMapLoaded) {
            cameraPositionState.animate(
                CameraUpdateFactory
                    .newLatLngZoom(locationItems[mapPosition].position, 18f)
            )
        }
    }
}
