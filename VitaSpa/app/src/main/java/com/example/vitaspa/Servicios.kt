package com.example.vitaspa

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class Servicios : AppCompatActivity() {

    lateinit var etId: EditText
    lateinit var etDescripcion: EditText
    lateinit var etCosto: EditText
    lateinit var etMaterial: EditText
    lateinit var etObservacion: EditText

    lateinit var btnAgregar: Button
    lateinit var btnConsultar: Button
    lateinit var btnModificar: Button
    lateinit var btnEliminar: Button
    lateinit var btnVerTodos: Button
    lateinit var lvServicios: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_servicios)

        etId = findViewById(R.id.etId)
        etDescripcion = findViewById(R.id.etDescripcion)
        etCosto = findViewById(R.id.etCosto)
        etMaterial = findViewById(R.id.etMaterial)
        etObservacion = findViewById(R.id.etObservacion)

        btnAgregar = findViewById(R.id.btnAgregar)
        btnConsultar = findViewById(R.id.btnConsultar)
        btnModificar = findViewById(R.id.btnModificar)
        btnEliminar = findViewById(R.id.btnEliminar)
        btnVerTodos = findViewById(R.id.btnVerTodos)
        lvServicios = findViewById(R.id.lvServicios)

        // --- ESCUCHA DE LOS BOTONES ---
        btnAgregar.setOnClickListener { agregarServicio() }
        btnConsultar.setOnClickListener { consultarServicio() }
        btnModificar.setOnClickListener { confirmarModificar() }
        btnEliminar.setOnClickListener { confirmarEliminar() }
        btnVerTodos.setOnClickListener { listarServicios() }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // estas funciones nos permiten navegar de la pantalla actual hacia los diferentes pantallas de la aplicacion
    fun home() {
        val pantalla1 = Intent(this, com.example.vitaspa.Menu::class.java)
        startActivity(pantalla1)
        finish()
    }
    fun nosotros() {
        val pantalla2 = Intent(this, Nosotros::class.java)
        startActivity(pantalla2)
        finish()
    }
    fun mapa() {
        val pantalla3 = Intent(this, MapaApi::class.java)
        startActivity(pantalla3)
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
            R.id.mapa -> mapa()
            R.id.mision -> mision()
            R.id.vision -> vision()
            R.id.logout -> logout()
        }
        return super.onOptionsItemSelected(item)
    }


    // ===== VALIDACIÓN DE CAMPOS =====
    private fun validInputsParaAltaOMod(): Boolean {
        val id = etId.text.toString().trim()
        val desc = etDescripcion.text.toString().trim()
        val costo = etCosto.text.toString().trim()

        if (id.length != 3) { toast("El ID debe tener 3 caracteres (ej: 001)"); return false }
        if (desc.isEmpty()) { toast("Ingresa la descripción"); return false }
        if (costo.isEmpty()) { toast("Ingresa el costo"); return false }
        return true
    }

    // ===== AGREGAR SERVICIO =====
    private fun agregarServicio() {
        if (!validInputsParaAltaOMod()) return

        val url = EndPoints.ADD_SERVICIO
        val req = object: StringRequest(
            Method.POST, url,
            { resp ->
                val obj = JSONObject(resp)
                toast(obj.optString("message", "OK"))
                limpiar()
                listarServicios()
            },
            { err -> toast("Error: ${err.message}") }
        ){
            override fun getParams(): MutableMap<String, String> {
                return hashMapOf(
                    "idservicio" to etId.text.toString().trim(),
                    "descripcion" to etDescripcion.text.toString().trim(),
                    "costo" to etCosto.text.toString().trim(),
                    "material" to etMaterial.text.toString().trim(),
                    "observacion" to etObservacion.text.toString().trim()
                )
            }
        }
        Volley.newRequestQueue(this).add(req)
    }

    // ===== CONSULTAR SERVICIO =====
    private fun consultarServicio() {
        val id = etId.text.toString().trim()
        if (id.length != 3) { toast("Ingresa un ID de 3 caracteres"); return }

        val url = EndPoints.GET_SERVICIO + "&idservicio=$id"
        val req = StringRequest(
            Request.Method.GET, url,
            { resp ->
                val obj = JSONObject(resp)
                if (!obj.optBoolean("error", true)) {
                    val s = obj.getJSONObject("servicio")
                    etDescripcion.setText(s.optString("descripcion",""))
                    etCosto.setText(s.optString("costo",""))
                    etMaterial.setText(s.optString("material",""))
                    etObservacion.setText(s.optString("observacion",""))
                    toast(obj.optString("message","Encontrado"))
                } else {
                    toast(obj.optString("message","No encontrado"))
                }
            },
            { err -> toast("Error: ${err.message}") }
        )
        Volley.newRequestQueue(this).add(req)
    }

    // ===== MODIFICAR SERVICIO =====
    private fun confirmarModificar() {
        if (!validInputsParaAltaOMod()) return
        AlertDialog.Builder(this)
            .setTitle("Confirmar")
            .setMessage("¿Seguro que quieres MODIFICAR este servicio?")
            .setPositiveButton("Sí") { _, _ -> modificarServicio() }
            .setNegativeButton("No", null)
            .show()
    }
    private fun modificarServicio() {
        val url = EndPoints.UPDATE_SERVICIO
        val req = object: StringRequest(
            Method.POST, url,
            { resp ->
                val obj = JSONObject(resp)
                toast(obj.optString("message","Actualizado"))
                listarServicios()
            },
            { err -> toast("Error: ${err.message}") }
        ){
            override fun getParams(): MutableMap<String, String> {
                return hashMapOf(
                    "idservicio" to etId.text.toString().trim(),
                    "descripcion" to etDescripcion.text.toString().trim(),
                    "costo" to etCosto.text.toString().trim(),
                    "material" to etMaterial.text.toString().trim(),
                    "observacion" to etObservacion.text.toString().trim()
                )
            }
        }
        Volley.newRequestQueue(this).add(req)
    }

    // ===== ELIMINAR SERVICIO =====
    private fun confirmarEliminar() {
        val id = etId.text.toString().trim()
        if (id.length != 3) { toast("Ingresa un ID de 3 caracteres"); return }
        AlertDialog.Builder(this)
            .setTitle("Confirmar")
            .setMessage("¿Seguro que quieres ELIMINAR el servicio $id?")
            .setPositiveButton("Sí") { _, _ -> eliminarServicio(id) }
            .setNegativeButton("No", null)
            .show()
    }
    private fun eliminarServicio(id: String) {
        val url = EndPoints.DELETE_SERVICIO
        val req = object: StringRequest(
            Method.POST, url,
            { resp ->
                val obj = JSONObject(resp)
                toast(obj.optString("message","Eliminado"))
                limpiar()
                listarServicios()
            },
            { err -> toast("Error: ${err.message}") }
        ){
            override fun getParams(): MutableMap<String, String> {
                return hashMapOf("idservicio" to id)
            }
        }
        Volley.newRequestQueue(this).add(req)
    }

    // ===== LISTAR TODOS LOS SERVICIOS (Con scroll en ScrollView) =====
    private fun listarServicios() {
        val url = EndPoints.LIST_SERVICIOS
        val req = StringRequest(
            Request.Method.GET, url,
            { resp ->
                val obj = JSONObject(resp)
                if (!obj.optBoolean("error", true)) {
                    val arr = obj.optJSONArray("servicios")
                    val items = ArrayList<String>()
                    if (arr != null) {
                        for (i in 0 until arr.length()) {
                            val s = arr.getJSONObject(i)
                            val linea = "${s.optString("idservicio","")} • ${s.optString("descripcion","")} • S/${s.optString("costo","")}"
                            items.add(linea)
                        }
                    }
                    lvServicios.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
                    // 🔹 Ajustamos la altura para que el ScrollView funcione correctamente
                    setListViewHeightBasedOnItems(lvServicios)
                } else {
                    toast(obj.optString("message","Sin datos"))
                    lvServicios.adapter = null
                }
            },
            { err -> toast("Error: ${err.message}") }
        )
        Volley.newRequestQueue(this).add(req)
    }

    // ===== AJUSTAR ALTURA DEL LISTVIEW =====
    private fun setListViewHeightBasedOnItems(listView: ListView) {
        val listAdapter = listView.adapter ?: return
        var totalHeight = 0
        for (i in 0 until listAdapter.count) {
            val listItem = listAdapter.getView(i, null, listView)
            listItem.measure(
                View.MeasureSpec.makeMeasureSpec(listView.width, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
            )
            totalHeight += listItem.measuredHeight
        }
        val params = listView.layoutParams
        params.height = totalHeight + (listView.dividerHeight * (listAdapter.count - 1))
        listView.layoutParams = params
        listView.requestLayout()
    }

    // ===== LIMPIAR CAMPOS =====
    private fun limpiar() {
        etDescripcion.setText("")
        etCosto.setText("")
        etMaterial.setText("")
        etObservacion.setText("")
    }

    // ===== TOAST =====
    private fun toast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

}