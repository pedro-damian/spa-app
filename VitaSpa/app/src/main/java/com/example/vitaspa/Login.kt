package com.example.vitaspa

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
            finish()
        }

        ingresar.setOnClickListener {
//            val pantalla2 = Intent(this, Menu::class.java)
//            startActivity(pantalla2)
//            System.exit(0)
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
        val url = "http://192.168.1.9/webapi/v1/index.php?op=login"  //  IP local

        val queue = Volley.newRequestQueue(this)
        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            { response ->
                val jsonObject = JSONObject(response)
                if (!jsonObject.getBoolean("error")) {
                    Toast.makeText(this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show()
                    // Redirigir a la pantalla de menú
                    val intent = Intent(this, Menu::class.java)  // Ajusta al nombre de tu activity de menú
                    startActivity(intent)
                    finish()  // Opcional: cierra el login
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