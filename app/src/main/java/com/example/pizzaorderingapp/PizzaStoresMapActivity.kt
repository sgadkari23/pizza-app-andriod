package com.example.pizzaorderingapp
/*
* Application Name: Pizza App
* Name : Supriya Gadkari & Raj Shahu
* Group No: 3
* Description: Pizza stores on google maps
* */
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.OpeningHours
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPhotoRequest
import com.google.android.libraries.places.api.net.FetchPhotoResponse
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.FetchPlaceResponse
import kotlinx.android.synthetic.main.activity_main.*

class PizzaStoresMapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pizza_stores_map)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.map_types,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.defaultType -> {
               mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
            }
            R.id.satellite -> {
                mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
            }
        }
        return true
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setInfoWindowAdapter(CustomInfoWindowForGoogleMap(this))
        val citySelected:String = intent.extras!!.get("storeCity") as String

        // Initialize the SDK
        Places.initialize(applicationContext, "AIzaSyBsB5J1cPKtF9IwE1x7ZcbkfV1K-jbxPJE")

        // Create a new PlacesClient instance
        val placesClient = Places.createClient(this)
        val placeFields = listOf<Place.Field>(
            Place.Field.ADDRESS,
            Place.Field.LAT_LNG,
            Place.Field.NAME,
            Place.Field.RATING,
            Place.Field.USER_RATINGS_TOTAL,
            Place.Field.WEBSITE_URI
        )

        // for loop for austin
        val cities = Cities()
        val austin = cities.getCity(citySelected)
        if (austin != null) {
            // set camera position
           // mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(austin.latLngBounds, 0))
            for(pizzaStoreId:String in austin.pizzaStoreIds)
            {
                val zoomLevel = 11.0f
                val request = FetchPlaceRequest.newInstance(pizzaStoreId, placeFields)
                placesClient.fetchPlace(request)
                    .addOnSuccessListener { response: FetchPlaceResponse ->
                        val place = response.place
                        val position: LatLng = place.latLng!!

                        val m = mMap.addMarker(MarkerOptions()
                            .position(position)
                            .title(place.name)
                            .snippet("Rating: " + place.rating)
                            .icon(BitmapDescriptorFactory.defaultMarker( BitmapDescriptorFactory.HUE_ORANGE)))

                        m.tag = InfoWindowData(place.name, place.address, place.rating, place.userRatingsTotal, place.openingHours)
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position,zoomLevel))

                    }.addOnFailureListener { exception: Exception ->
                        if (exception is ApiException) {
                            val statusCode = exception.statusCode
                            TODO("Handle error with given status code")
                        }
                    }
            }
        }
    }
}

class CustomInfoWindowForGoogleMap : GoogleMap.InfoWindowAdapter {
    var mContext:Context? = null
    constructor(context: Context){
        mContext = context
    }

    override fun getInfoContents(marker: Marker): View {
        var mWindow = (mContext as Activity).layoutInflater.inflate(R.layout.map_marker_info_window, null)
        val title = mWindow.findViewById<TextView>(R.id.info_window_title)
        val infoWindowData:InfoWindowData = marker.tag as InfoWindowData
        title.text = infoWindowData.title

        val rating= mWindow.findViewById<TextView>(R.id.info_window_rating)
        rating.text = "Rating: "+infoWindowData.rating.toString()+" ("+infoWindowData.ratingsTotal+")"

        //val openingHours= mWindow.findViewById<TextView>(R.id.info_window_openingHours)
       // openingHours.text = "Rating: "+infoWindowData.openingHours.toString()

        val address= mWindow.findViewById<TextView>(R.id.info_window_address)
        address.text = infoWindowData.address
        //val weekDayText = this.openingHours?.weekdayText
        return mWindow
    }

    override fun getInfoWindow(marker: Marker): View? {
        return null
    }
}

class InfoWindowData
{
    var title:String?
    var address:String?
    var rating:Double?
    var ratingsTotal:Int?
    var openingHours:OpeningHours?
    constructor(title:String?, address:String?, rating:Double?, ratingsTotal:Int?, openingHours: OpeningHours?){
        this.title = title
        this.address = address
        this.rating = rating
        this.ratingsTotal = ratingsTotal
        this.openingHours = openingHours
    }
}