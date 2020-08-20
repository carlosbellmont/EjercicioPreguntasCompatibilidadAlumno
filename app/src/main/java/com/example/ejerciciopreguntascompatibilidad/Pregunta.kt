package com.example.ejerciciopreguntascompatibilidad

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity

data class Pregunta (val pregunta : String, val respuesta1 : String, val respuesta2 : String, val respuestaCorrecta : Int, var respuestaUser: Int = -1) {

    @PrimaryKey(autoGenerate = true)

    var id : Int = 0

    fun respuestaEsCorrecta(): Boolean {
        return respuestaCorrecta == respuestaUser
    }
}