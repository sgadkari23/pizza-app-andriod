package com.example.pizzaorderingapp
/*
* Application Name: Pizza App
* Name : Supriya Gadkari & Raj Shahu
* Group No: 3
* Description: Pizza stores on google maps
* */
import android.app.Activity
import android.content.Context
import android.location.Location
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
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
            android.R.id.home ->{
                onBackPressed()
                return true
            }
        }
        return true
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        // setting infowindow when clicked om marker
        mMap.setInfoWindowAdapter(CustomInfoWindowForGoogleMap(this))
        val citySelected:String = intent.extras!!.get("storeCity") as String

        // Initialize the SDK
        Places.initialize(applicationContext, getString(R.string.google_maps_key))

        // Create a new PlacesClient instance
        val placesClient = Places.createClient(this)

        // Data that to returned from places API
        val placeFields = listOf<Place.Field>(
            Place.Field.ADDRESS,
            Place.Field.LAT_LNG,
            Place.Field.NAME,
            Place.Field.RATING,
            Place.Field.USER_RATINGS_TOTAL
        )

        // for loop for cities
        val cities = Cities()
        // access city from hashmap
        val city = cities.getCity(citySelected)
        if (city != null) {

            // Add home marker
            val homeMarker = mMap.addMarker(MarkerOptions()
                .position(city.customerHomeAddress!!)
                .title("Home")
                .icon(BitmapDescriptorFactory.defaultMarker( BitmapDescriptorFactory.HUE_GREEN)))
            homeMarker.tag = InfoWindowData("Home", null, null, null, null, null)


            for(pizzaStoreId:String in city.pizzaStoreIds)
            {   // set camera
                val zoomLevel = 11.0f
                // request to place api
                val request = FetchPlaceRequest.newInstance(pizzaStoreId, placeFields)
                placesClient.fetchPlace(request)
                    .addOnSuccessListener { response: FetchPlaceResponse ->
                        val place = response.place
                        val position: LatLng = place.latLng!!

                        val locationA = Location("Home")
                        locationA.setLatitude(city.customerHomeAddress?.latitude!!)
                        locationA.setLongitude(city.customerHomeAddress?.longitude!!)
                        val locationB = Location("pizzaStore")
                        locationB.setLatitude(position.latitude)
                        locationB.setLongitude(position.longitude)
                        val deliveryDistance:Double =  locationA.distanceTo(locationB)*1.0 / 1000
                        val AVERAGE_SPEED = 20.0 // km/hr
                        val deliveryTime = (deliveryDistance/AVERAGE_SPEED * 60).toInt()

                        // add marker
                        val m = mMap.addMarker(MarkerOptions()
                            .position(position)
                            .title(place.name)
                            .snippet("Rating: " + place.rating)
                            .icon(BitmapDescriptorFactory.defaultMarker( BitmapDescriptorFactory.HUE_ORANGE)))
                        // Send data to infoWindow
                        m.tag = InfoWindowData(place.name, place.address, place.rating, place.userRatingsTotal, place.openingHours, deliveryTime = deliveryTime)
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
    //inflating infoWindow
    override fun getInfoContents(marker: Marker): View {
        var mWindow = (mContext as Activity).layoutInflater.inflate(R.layout.map_marker_info_window, null)
        val title = mWindow.findViewById<TextView>(R.id.info_window_title)

        // accesssing the data passed  from marker
        val infoWindowData:InfoWindowData = marker.tag as InfoWindowData
        title.text = infoWindowData.title

        val rating= mWindow.findViewById<TextView>(R.id.info_window_rating)
        if(infoWindowData.rating!=null)
            rating.text = "Rating: "+infoWindowData.rating.toString()+" ("+infoWindowData.ratingsTotal+")"
        else
            rating.setVisibility(View.INVISIBLE)

        val address= mWindow.findViewById<TextView>(R.id.info_window_address)
        if(infoWindowData.address!=null)
            address.text = infoWindowData.address
        else
            address.setVisibility(View.INVISIBLE)

        val deliveryTimeTextView= mWindow.findViewById<TextView>(R.id.deliveryTimeTextView)
        if(infoWindowData.deliveryTime!=null)
            deliveryTimeTextView.text = "Estimated Time for Home Delivery: " + infoWindowData.deliveryTime.toString() + " min"
        else
            deliveryTimeTextView.setVisibility(View.INVISIBLE)

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
    var deliveryTime:Int?

    constructor(title:String?, address:String?, rating:Double?, ratingsTotal:Int?, openingHours: OpeningHours?, deliveryTime:Int?){
        this.title = title
        this.address = address
        this.rating = rating
        this.ratingsTotal = ratingsTotal
        this.openingHours = openingHours
        this.deliveryTime = deliveryTime
    }
}