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

        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN)
        // Add a marker in Sydney and move the camera
        val vitaspa = LatLng(-12.05211808310037, -76.9653540010701)
        mMap.addMarker(MarkerOptions().position(vitaspa).title("Vita Spa"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(vitaspa, 15f))
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
        finish()
    }
    fun nosotros() {
        val pantalla2 = Intent(this, Nosotros::class.java)
        startActivity(pantalla2)
        finish()
    }

    fun servicios() {
        val pantalla4 = Intent(this, Servicios::class.java)
        startActivity(pantalla4)
        finish()
    }
    fun mision() {
        val pantalla5 = Intent(this, Mision::class.java)
        startActivity(pantalla5)
        finish()
    }

    fun vision() {
        val pantalla6 = Intent(this, Vision::class.java)
        startActivity(pantalla6)
        finish()
    }
    fun logout() {
        val pantalla7 = Intent(this, Login::class.java)
        pantalla7.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK // Limpia el stack de actividades
        startActivity(pantalla7)
        finish()
    }

    // este metodo evalua que accion va tomar cuando se selecciona un item del menu opciones y esto lo hace mediante el ID del elemento seleccionado
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.home -> home()
            R.id.nosotros -> nosotros()
            R.id.mision -> mision()
            R.id.servicios -> servicios()
            R.id.vision -> vision()
            R.id.logout -> logout()

        }
        return super.onOptionsItemSelected(item)
    }
}