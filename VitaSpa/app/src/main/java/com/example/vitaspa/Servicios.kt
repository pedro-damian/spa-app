package com.example.vitaspa

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

class Servicios : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_servicios)
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
    fun nosotros() {
        val pantalla2 = Intent(this, Nosotros::class.java)
        startActivity(pantalla2)
        System.exit(0)
    }
    fun mapa() {
        val pantalla3 = Intent(this, MapaApi::class.java)
        startActivity(pantalla3)
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
            R.id.mapa -> mapa()
            R.id.mision -> mision()
            R.id.vision -> vision()
            //R.id.logout -> logout()
        }
        return super.onOptionsItemSelected(item)
    }

}