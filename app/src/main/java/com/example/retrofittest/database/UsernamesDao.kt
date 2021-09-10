package com.example.retrofittest.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.retrofittest.database_tables.Usernames

@Dao
interface UsernamesDao {

    //scheinbar bei queries kein suspend fun sondern nur fun
    // Flow statt LiveData falls man kontinuierlichen datenstrom hat statt batch weise aufrufe
    @Query("SELECT * FROM usernames ORDER BY username ASC")
    fun getAllUsernames(): LiveData<List<Usernames>>

    //rest to add
}