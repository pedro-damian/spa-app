package com.example.vitaspa

import android.content.Intent
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
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> {
                startActivity(Intent(this, Menu::class.java))
                return true
            }
            R.id.mapa -> {
                startActivity(Intent(this, Menu::class.java))
                return true
            }
            R.id.logout -> {
                startActivity(Intent(this, Menu::class.java))
                return true
            }

            R.id.nosotros -> {
                startActivity(Intent(this, Menu::class.java))
                return true
            }
            R.id.mision -> {
                startActivity(Intent(this, Menu::class.java))
                return true
            }
            R.id.vision-> {
                startActivity(Intent(this, Menu::class.java))
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}