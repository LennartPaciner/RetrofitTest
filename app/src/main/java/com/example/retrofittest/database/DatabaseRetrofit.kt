package com.example.retrofittest.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.retrofittest.api.TodoApi
import com.example.retrofittest.database_tables.Passwords
import com.example.retrofittest.database_tables.Usernames
import kotlinx.coroutines.CoroutineScope

// für migration zu neuer db version: https://medium.com/androiddevelopers/understanding-migrations-with-room-f01e04b07929
@Database(entities = arrayOf(Usernames::class, Passwords::class), version = 1)
abstract class DatabaseRetrofit: RoomDatabase() {

    fun todoApi(): TodoApi {
        return TodoApi.getInstance()
    }

    abstract fun databaseDao(): DatabaseDao

    // verhindert öffnen von mehreren database instanzen gleichzeitig z.b. in unterschiedlichen threads!
    // evtl durch dagger hilt nicht notwendig siehe wander app yt typ
    companion object {
        @Volatile
        private var INSTANCE: DatabaseRetrofit ?= null

        fun getDatabase(context: Context, scope: CoroutineScope) : DatabaseRetrofit {
            // falls instanz != null : return, sonst erstelle
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DatabaseRetrofit::class.java,
                    "Datenbank"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }

    //mal video noch zu callback von room database anschauen -> siehe kotlinapp beispiel
    private class DatabaseRetrofitCallback(private val scope: CoroutineScope): RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
        }
    }

}






















