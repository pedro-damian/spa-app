package com.example.vitaspa

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.vitaspa.databinding.ActivityMapaApiBinding

class MapaApi : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapaApiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapaApiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE)
        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 20f))
        mMap.uiSettings.isZoomControlsEnabled = true
    }


    override fun onCreateOptionsMenu(menu: android.view.Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // estas funciones nos permiten navegar de la pantalla actual hacia los diferentes pantallas de la aplicacion
    fun home() {
        val pantalla1 = Intent(this, Menu::class.java)
        startActivity(pantalla1)
        System.exit(0)
    }
    fun nosotros() {
        val pantalla2 = Intent(this, Nosotros::class.java)
        startActivity(pantalla2)
        System.exit(0)
    }

    fun servicios() {
        val pantalla4 = Intent(this, Servicios::class.java)
        startActivity(pantalla4)
        System.exit(0)
    }
    fun mision() {
        val pantalla5 = Intent(this, Mision::class.java)
        startActivity(pantalla5)
        System.exit(0)
    }

    fun vision() {
        val pantalla6 = Intent(this, Vision::class.java)
        startActivity(pantalla6)
        System.exit(0)
    }
//    fun logout() {
//        val pantalla7 = Intent(this, PagoConsumo::class.java)
//        startActivity(pantalla7)
//        System.exit(0)
//    }

    // este metodo evalua que accion va tomar cuando se selecciona un item del menu opciones y esto lo hace mediante el ID del elemento seleccionado
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.home -> home()
            R.id.nosotros -> nosotros()
            R.id.mision -> mision()
            R.id.servicios -> servicios()
            R.id.vision -> vision()
            //R.id.logout -> logout()

        }
        return super.onOptionsItemSelected(item)
    }
}