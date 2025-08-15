package com.example.vitaspa

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import com.android.volley.Request

class Login : AppCompatActivity() {

    lateinit var usuario: EditText
    lateinit var clave: EditText
    lateinit var ingresar:Button
    lateinit var cerrar:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        usuario = findViewById(R.id.etUsuario)
        clave = findViewById(R.id.etClave)
        ingresar = findViewById(R.id.btnIngresar)
        cerrar = findViewById(R.id.btnCerrar)


        cerrar.setOnClickListener {
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            builder
                .setMessage("Fin de la aplicacion")
                .setTitle("Cerrar aplicacion")
                .setPositiveButton(android.R.string.yes){dialog, which -> Toast.makeText(applicationContext, android.R.string.yes, Toast.LENGTH_SHORT).show()
                    System.exit(0) }
                .setNegativeButton(android.R.string.no){dialog, which -> Toast.makeText(applicationContext, "Cancelado", Toast.LENGTH_SHORT).show()}
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }


        ingresar.setOnClickListener {

            val username = usuario.text.toString().trim()
            val password = clave.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Ingresa usuario y contraseña", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            login(username, password)
        }

    }

    private fun login(username: String, password: String) {
        val url = EndPoints.LOGIN

        val queue = Volley.newRequestQueue(this)
        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            { response ->
                val jsonObject = JSONObject(response)
                if (!jsonObject.getBoolean("error")) {
                    Toast.makeText(this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show()
                    // Redirigir a la pantalla de menú
                    val intent = Intent(this, Menu::class.java)
                    startActivity(intent)
                    finish()  // cierra la actividad del login
                } else {
                    Toast.makeText(this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show()
                }
            },
            { error ->
                Toast.makeText(this, "Error de conexión: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        ) {
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                params["username"] = username
                params["password"] = password
                return params
            }
        }

        queue.add(stringRequest)
    }
}