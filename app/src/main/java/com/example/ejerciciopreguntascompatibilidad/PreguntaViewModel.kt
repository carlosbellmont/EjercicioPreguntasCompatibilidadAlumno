package com.example.ejerciciopreguntascompatibilidad

import android.content.Context
import androidx.lifecycle.ViewModel
import com.cbellmont.ejercicioandroidcuestionario.App
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.text.FieldPosition


class PreguntaViewModel : ViewModel() {

    suspend fun getPregunta(position: Int, context: Context): Pregunta = withContext(IO) {

        return@withContext App.getDatabase(context).preguntaDao().getAll()[position]

    }

    suspend fun sizeListPregunta( context: Context): Int = withContext(IO) {

        return@withContext App.getDatabase(context).preguntaDao().getAll().size

    }

    suspend fun actualizarPregunta (pregunta: Pregunta, context: Context) = withContext(IO) {

        App.getDatabase(context).preguntaDao().update(pregunta)
    }


    suspend fun calcularCompatibilidad( context: Context): String = withContext(IO) {

        var contador = 0

        App.getDatabase(context).preguntaDao().getAll().forEach {
            if (it.respuestaEsCorrecta()) {
                contador++
            }
        }
        return@withContext when (contador) {
            0 -> "No teneis futuro"
            1 -> "Podria ser mejor"
            2 -> "No esta mal"
            3 -> "Buena relacion"
            4 -> "Estais muy enamorados"
            else -> "Error"
        }
    }








}