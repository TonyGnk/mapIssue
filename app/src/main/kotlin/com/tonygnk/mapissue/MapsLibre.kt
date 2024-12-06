package com.tonygnk.mapissue

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.Fragment
import org.maplibre.android.MapLibre
import org.maplibre.android.camera.CameraPosition
import org.maplibre.android.geometry.LatLng
import org.maplibre.android.maps.MapLibreMap
import org.maplibre.android.maps.MapView
import org.maplibre.android.maps.Style

@Composable
fun MapsLibre() {
    AndroidView(
        factory = { context ->
            val mapView = MapView(context)
            mapView.apply {
                onCreate(null)
                getMapAsync { map ->
                    try {
                        val styleFile = MapStyleManager(context).setupStyle()
                        if (styleFile is MapStyleManager.StyleSetupResult.Success) {
                            map.setStyle(
                                Style.Builder()
                                    .fromUri(Uri.fromFile(styleFile.styleFile).toString())
                            ) { style ->
                                Log.d("MapsLibre", "Style loaded successfully")
                            }
                        }

                        map.cameraPosition = CameraPosition.Builder()
                            .target(LatLng(40.631619, 22.953482))
                            .zoom(14.0)
                            .build()

                    } catch (e: Exception) {
                        Log.e("MapsLibre", "Error loading map: ${e.message}", e)
                    }
                }
            }
            mapView
        },
        update = { mapView ->
            // Ensure lifecycle methods are properly handled
            mapView.onResume()
        }
    )
}

class MapsFragment : Fragment() {
    private lateinit var mapView: MapView
    private lateinit var maplibreMap: MapLibreMap
    private val TAG = "MapsFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_forge_maps, container, false)

        try {
            MapLibre.getInstance(requireContext())
            Log.d(TAG, "MapLibre initialized successfully")
        } catch (e: Exception) {
            Log.e(TAG, "Error initializing MapLibre: ${e.message}", e)
        }

        mapView = view.findViewById(R.id.mapView)


        mapView.onCreate(savedInstanceState)

        mapView.getMapAsync { map ->
            maplibreMap = map
            try {
                val styleFile = MapStyleManager(this.requireContext()).setupStyle()
                if (styleFile is MapStyleManager.StyleSetupResult.Success) {
                    map.setStyle(
                        Style.Builder().fromUri(Uri.fromFile(styleFile.styleFile).toString())
                    ) { style ->
                        Log.d(TAG, "Style loaded successfully")
                    }
                }

                map.cameraPosition = CameraPosition.Builder()
                    .target(LatLng(40.631619, 22.953482))
                    .zoom(14.0)//12
                    .build()

            } catch (e: Exception) {
                Log.e(TAG, "Error loading map: ${e.message}", e)
            }
        }

        return view
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mapView.onDestroy()
    }
}
