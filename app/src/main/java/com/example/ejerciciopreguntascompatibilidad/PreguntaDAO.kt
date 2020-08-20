package com.example.ejerciciopreguntascompatibilidad

import androidx.room.*

@Dao

interface PreguntaDAO {

    @Query("SELECT * FROM Pregunta")

    fun getAll(): List<Pregunta>

    @Insert

    fun insert(pregunta: Pregunta)

    @Insert

    fun insertAll(pregunta: List<Pregunta>)

    @Update

    fun update(pregunta: Pregunta)

    @Delete

    fun delete(pregunta: Pregunta)
}