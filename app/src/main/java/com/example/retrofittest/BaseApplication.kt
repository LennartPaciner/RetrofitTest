package com.example.retrofittest

import android.app.Application
import com.example.retrofittest.database.DatabaseRetrofit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

// für dependy injection dann benutzen wenn gelernt
class BaseApplication : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { DatabaseRetrofit.getDatabase(this, applicationScope) }

    //val repository by lazy { WordRepository(database.wordDao()) } -> evtl hier repo initialisieren dann brauch ichs in den fragmenten
    //nicht mehr zu initialisieren? umbauen morgen und testen obs so geht - siehe kotlinapp beispiel
}