package cubex.mahesh.googlemaps_mar9am19

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var gMap:GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var sFrag : SupportMapFragment = supportFragmentManager.
            findFragmentById(R.id.frag1) as SupportMapFragment
        sFrag.getMapAsync{
                        gMap = it
        }

        getLocation.setOnClickListener {
                        getCurrentLocation()
        }
    } // onCreate(  )

    @SuppressLint("MissingPermission")
    fun  getCurrentLocation( )
    {
            var lManager = getSystemService(
                 Context.LOCATION_SERVICE) as LocationManager
            lManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                1000L, 1f,
                object : LocationListener {
                    override fun onLocationChanged(loc: Location?) {
                        tv_lati.setText("Latitude : "+loc?.latitude)
                        tv_longi.setText("Longitude : "+loc?.longitude)
                        lManager.removeUpdates(this)

                        var moptions = MarkerOptions( )
                        moptions.position(LatLng(loc!!.latitude,loc!!.longitude))
                        moptions.icon(BitmapDescriptorFactory.fromResource(
                            R.drawable.car
                        ))
                        gMap?.addMarker(moptions)
                        gMap?.animateCamera(CameraUpdateFactory.
                newLatLngZoom(LatLng(loc!!.latitude,loc!!.longitude),15f))

                    }
                 override fun onProviderEnabled(p0: String?) {
                   }
                    override fun onProviderDisabled(p0: String?) {
                    }
                    override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {
                    }
                }
            )


    }


}
