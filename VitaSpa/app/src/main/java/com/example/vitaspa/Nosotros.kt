package com.example.vitaspa

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

class Nosotros : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nosotros)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // estas funciones nos permiten navegar de la pantalla actual hacia los diferentes pantallas de la aplicacion
    fun home() {
        val pantalla1 = Intent(this, com.example.vitaspa.Menu::class.java)
        startActivity(pantalla1)
        System.exit(0)
    }

    fun mapa() {
        val pantalla3 = Intent(this, Mapa::class.java)
        startActivity(pantalla3)
        val gmmIntentUri = Uri.parse("geo:0,0?q=-13.163225249955481, -72.54577669455493(MachuPicchu)")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
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
            R.id.mapa -> mapa()
            R.id.servicios -> servicios()
            R.id.mision -> mision()
            R.id.vision -> vision()
            //R.id.logout -> logout()

        }
        return super.onOptionsItemSelected(item)
    }
}