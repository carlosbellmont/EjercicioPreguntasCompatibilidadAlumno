package com.cbellmont.ejercicioandroidcuestionario

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.ejerciciopreguntascompatibilidad.Pregunta
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class App : Application() {

    companion object {
        var db: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {

            db?.let { return it }

            db = databaseBuilder(context, AppDatabase::class.java, "database")
                .addCallback(getCallback())
                .build()

            return db as AppDatabase
        }

        fun getCallback(): RoomDatabase.Callback {
            return object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    CoroutineScope(IO).launch {
                        withContext(IO) {
                            val preguntas: List<Pregunta> = listOf(
                                Pregunta(
                                    "¿ A que lugares te gustaria viajar?",
                                    "Sin ti a cualquier lado",
                                    "Contigo a cualquier lado",
                                    1
                                ),
                                Pregunta(
                                    "¿Que musica escuchas para relajarte?",
                                    "Tu dulce voz",
                                    "Cualquier cosa menos tus berridos",
                                    0
                                ),
                                Pregunta(
                                    "¿Con cual de mi familiares te llevas mejor?",
                                    "Con tu madre",
                                    "Imposible elegir entre tanto horror",
                                    0
                                ),
                                Pregunta(
                                    "¿Que te gusta hacer en tu tiempo libre?",
                                    "Estar contigo",
                                    "Preparar mi plan de fuga",
                                    0
                                )
                            )
                            App.db?.preguntaDao()?.insertAll(preguntas)
                        }
                    }
                }
                override fun onOpen(db: SupportSQLiteDatabase) {
                }
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database")
            .addCallback(getCallback())
            .build()
    }

}


