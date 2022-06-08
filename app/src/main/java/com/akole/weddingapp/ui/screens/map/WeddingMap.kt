package com.akole.weddingapp.ui.screens.map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.*
import kotlinx.coroutines.launch

@Composable
fun WeddingMap(
    mapPosition: Int,
    locationUIItems: List<LocationUIItem>,
    modifier: Modifier = Modifier
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
        position = CameraPosition.fromLatLngZoom(locationUIItems[mapPosition].position, 13f)
    }

    GoogleMap(
        modifier = modifier
            .fillMaxSize(),
        properties = properties,
        uiSettings = uiSettings,
        cameraPositionState = cameraPositionState,
        onMapLoaded = {
            coroutineScope.launch {
                cameraPositionState.animate(
                    CameraUpdateFactory
                        .newLatLngZoom(locationUIItems[mapPosition].position, 18f)
                )
                isMapLoaded = true
            }
        }
    ){
        locationUIItems.forEach { locationItem ->
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
            // Initial Map animation in order to zoom to the first position
            cameraPositionState.animate(
                CameraUpdateFactory
                    .newLatLngZoom(locationUIItems[mapPosition].position, 18f)
            )
        }
    }
}
