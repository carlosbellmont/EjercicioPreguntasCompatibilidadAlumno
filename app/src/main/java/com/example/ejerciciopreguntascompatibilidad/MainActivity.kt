package com.example.ejerciciopreguntascompatibilidad

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

interface MainInterface {
    fun enableButton()
    fun actualizarPregunta(pregunta: Pregunta)
}

class MainActivity : AppCompatActivity(), MainInterface {

    private lateinit var adapter: PreguntaAdapter
    private lateinit var model: PreguntaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        model = ViewModelProvider(this).get(PreguntaViewModel::class.java)

        createRecyclerView()

        buttonCompatibilidad.setOnClickListener {

            CoroutineScope(Main).launch {
                withContext(Main) {
                    val popUpAlert = AlertDialog.Builder(this@MainActivity)
                    popUpAlert.setTitle("Bienvenido")
                    popUpAlert.setMessage("Tu resultado ha sido ${model.calcularCompatibilidad(this@MainActivity)}")
                    popUpAlert.setPositiveButton("Seguir con la pareja") { dialogo, it ->
                        Toast.makeText(this@MainActivity, "Enhorabuena!!!", Toast.LENGTH_SHORT).show() }
                    popUpAlert.setNegativeButton("Terminar con la relacion")
                    { _, it -> Toast.makeText(this@MainActivity, "Muchas suerte", Toast.LENGTH_SHORT).show() }
                    popUpAlert.show()
                }
            }
        }

        CoroutineScope(IO).launch {
            withContext(IO) {
                for (i in 0 until model.sizeListPregunta(this@MainActivity)) {

                    val pregunta = model.getPregunta(i, this@MainActivity)
                    withContext(Main) {
                        adapter.addPregunta(pregunta) }
                }
                withContext(Main) {
                    buttonCompatibilidad.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE
                }
            }
        }
    }

    private fun createRecyclerView() {
        adapter = PreguntaAdapter(this)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    override fun enableButton() {
        buttonCompatibilidad.isEnabled = true
    }

    override fun actualizarPregunta(pregunta: Pregunta) {
        CoroutineScope(IO).launch {
            model.actualizarPregunta(pregunta, this@MainActivity)
        }
    }
}