package at.bwappsandmore.gackerlsackerlapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.toBitmap
import dagger.hilt.android.AndroidEntryPoint
import org.osmdroid.api.IMapController
import org.osmdroid.config.IConfigurationProvider
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.views.MapView
import org.osmdroid.views.CustomZoomButtonsController
import org.osmdroid.views.overlay.MapEventsOverlay
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay
import javax.inject.Inject

@AndroidEntryPoint
class MapFragment : Fragment() {

    @Inject
    lateinit var iConfigurationProvider: IConfigurationProvider

    @Inject
    lateinit var map: MapView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_map, container, false)
        map = view.findViewById(R.id.mapView)

        map.apply {
            setUseDataConnection(true)
            setTileSource(TileSourceFactory.MAPNIK)
            zoomController.setVisibility(CustomZoomButtonsController.Visibility.NEVER)
            setMultiTouchControls(true)
        }

        val mapController: IMapController = map.controller
        mapController.setZoom(19.5)

        val mGpsMyLocationProvider = GpsMyLocationProvider(activity)
        val mLocationOverlay = MyLocationNewOverlay(mGpsMyLocationProvider, map)

        val icon = context?.let {
            AppCompatResources.getDrawable(it, R.drawable.ic_baseline_pets_24)
                ?.toBitmap()
        }
        mLocationOverlay.apply {
            setPersonIcon(icon)
            enableMyLocation()
            enableFollowLocation()

        }

        map.overlays.add(mLocationOverlay)

        mLocationOverlay.runOnFirstFix {
            activity?.runOnUiThread(Runnable {
                map.overlays.apply {
                    clear()
                    add(mLocationOverlay)
                }
                mapController.apply {
                    setCenter(mLocationOverlay.myLocation)
                    animateTo(mLocationOverlay.myLocation)
                }
            })
        }
        val mapEventsReceiver = MapEventsReceiverImpl()
        val mapEventsOverlay = MapEventsOverlay(mapEventsReceiver)
        map.overlays.add(mapEventsOverlay)

        return view
    }

    override fun onPause() {
        super.onPause()
        map.onPause()
    }

    override fun onResume() {
        super.onResume()
        map.onResume()
    }
}