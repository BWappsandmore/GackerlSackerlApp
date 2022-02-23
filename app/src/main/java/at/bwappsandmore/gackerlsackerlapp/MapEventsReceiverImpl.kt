package at.bwappsandmore.gackerlsackerlapp

import android.util.Log
import org.osmdroid.events.MapEventsReceiver
import org.osmdroid.util.GeoPoint
import javax.inject.Inject

class MapEventsReceiverImpl @Inject constructor(): MapEventsReceiver {

    override fun singleTapConfirmedHelper(p: GeoPoint?): Boolean {
        Log.i("singleTapConfirmedHelper", "${p?.latitude} - ${p?.longitude}")
        return true
    }

    override fun longPressHelper(p: GeoPoint?): Boolean {
        Log.i("longPressHelper", "${p?.latitude} - ${p?.longitude}")
        return false
    }
}