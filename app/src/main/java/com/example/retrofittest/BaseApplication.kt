package com.example.retrofittest

import android.app.Application
import com.example.retrofittest.database.DatabaseRetrofit
import com.example.retrofittest.repository.DatabaseRepository
import com.example.retrofittest.repository.TodoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

// für dependy injection dann benutzen wenn gelernt
class BaseApplication : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { DatabaseRetrofit.getDatabase(this, applicationScope) }

    val retrofitRepository by lazy { TodoRepository(database.todoApi()) }

    val databaseRepository by lazy { DatabaseRepository(database.databaseDao()) }
}