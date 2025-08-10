package com.example.vitaspa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class Login : AppCompatActivity() {

    lateinit var usuario: EditText
    lateinit var clave: EditText
    lateinit var ingresar:Button
    lateinit var regresar:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        usuario = findViewById(R.id.etUsuario)
        clave = findViewById(R.id.etClave)
        ingresar = findViewById(R.id.btnIngresar)
        regresar = findViewById(R.id.btnRegresar)


        regresar.setOnClickListener {
            val pantalla1 = Intent(this, MainActivity::class.java)
            startActivity(pantalla1)
            System.exit(0)
        }
    }
}