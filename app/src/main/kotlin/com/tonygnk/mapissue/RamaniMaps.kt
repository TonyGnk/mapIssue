package com.tonygnk.mapissue

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import org.maplibre.android.geometry.LatLng
import org.maplibre.android.maps.Style
import org.ramani.compose.CameraPosition
import org.ramani.compose.MapLibre
import org.ramani.compose.MapProperties
import org.ramani.compose.UiSettings


@Composable
fun RamaniMaps() {
    val context = LocalContext.current
    val style = when (val result = MapStyleManager(context).setupStyle()) {
        is MapStyleManager.StyleSetupResult.Error -> {
            throw result.exception
        }

        is MapStyleManager.StyleSetupResult.Success -> result.styleFile
    }
    val styleBuilder = Style.Builder().fromUri(
        Uri.fromFile(style).toString()
    )

    val uiSettings = rememberSaveable {
        UiSettings(
            isLogoEnabled = false,
            isAttributionEnabled = false,
            rotateGesturesEnabled = false,
        )
    }
    val mapProperties = rememberSaveable {
        MapProperties(
            minZoom = 11.0,
            minPitch = 11.0,
            maxZoom = 18.0,
            maxPitch = 18.0,
        )
    }
    val cameraPosition = rememberSaveable {
        CameraPosition(
            target = LatLng(40.62, 22.95),
            zoom = 15.0,
        )
    }

    MapLibre(
        uiSettings = uiSettings,
        properties = mapProperties,
        cameraPosition = cameraPosition,
        modifier = Modifier,
        styleBuilder = styleBuilder,
    ) {}
}
