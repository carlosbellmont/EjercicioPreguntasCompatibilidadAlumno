package com.cbellmont.ejercicioandroidcuestionario

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ejerciciopreguntascompatibilidad.Pregunta
import com.example.ejerciciopreguntascompatibilidad.PreguntaDAO

@Database(entities = [Pregunta::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun preguntaDao(): PreguntaDAO
}



