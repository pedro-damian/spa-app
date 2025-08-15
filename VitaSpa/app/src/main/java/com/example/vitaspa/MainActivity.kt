package com.example.vitaspa

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var btnContinuar: Button
    lateinit var btnCerrar: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnContinuar = findViewById(R.id.btn_continuar)


        // crea una funcion para navegar de la pantalla actual hacia la pantalla Menu
        btnContinuar.setOnClickListener {
            val pantalla1 = Intent(this, Login::class.java)
            startActivity(pantalla1)
            System.exit(0)
        }


    }
}