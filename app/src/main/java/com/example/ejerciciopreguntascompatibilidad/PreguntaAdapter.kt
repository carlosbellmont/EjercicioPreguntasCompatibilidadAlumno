package com.example.ejerciciopreguntascompatibilidad

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PreguntaAdapter(var mainInterface: MainInterface) :
    RecyclerView.Adapter<PreguntaAdapter.PreguntaViewHolder>() {

    private var listaPreguntas: MutableList<Pregunta> = mutableListOf()

    class PreguntaViewHolder(
        val root: View,
        val textViewPregunta: TextView,
        val radioButtonRespuesta1: RadioButton,
        val radioButtonRespuesta2: RadioButton
    ) : RecyclerView.ViewHolder(root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreguntaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itempregunta, parent, false)
        val pregunta = view.findViewById<TextView>(R.id.pregunta)
        val respuesta1 = view.findViewById<RadioButton>(R.id.rbrespuesta1)
        val respuesta2 = view.findViewById<RadioButton>(R.id.rbrespuesta2)

        return PreguntaViewHolder(view, pregunta, respuesta1, respuesta2)
    }

    override fun getItemCount(): Int {
        return listaPreguntas.size
    }

    override fun onBindViewHolder(holder: PreguntaViewHolder, position: Int) {
        holder.textViewPregunta.text = listaPreguntas[position].pregunta
        holder.radioButtonRespuesta1.text = listaPreguntas[position].respuesta1
        holder.radioButtonRespuesta2.text = listaPreguntas[position].respuesta2
        if (listaPreguntas[position].respuestaUser == 0) {
            holder.radioButtonRespuesta1.isChecked = true
        }
        if (listaPreguntas[position].respuestaUser == 1) {
            holder.radioButtonRespuesta2.isChecked = true
        }
        holder.radioButtonRespuesta1.setOnClickListener {

            listaPreguntas[position].respuestaUser = 0
            var respuestaNull = false
            listaPreguntas.forEach() {
                if (it.respuestaUser == -1) {
                    respuestaNull = true }
            }
            if (respuestaNull == false) {
                mainInterface.enableButton() }
            mainInterface.actualizarPregunta(listaPreguntas[position])
        }

        holder.radioButtonRespuesta2.setOnClickListener {

            listaPreguntas[position].respuestaUser = 1
            var respuestaNull = false
            listaPreguntas.forEach() {
                if (it.respuestaUser == -1) {
                    respuestaNull = true }
            }
            if (respuestaNull == false) {
                mainInterface.enableButton() }
            mainInterface.actualizarPregunta(listaPreguntas[position])
        }
    }

    fun addPregunta(pregunta: Pregunta) {

        listaPreguntas.add(pregunta)
        notifyDataSetChanged()
    }
}