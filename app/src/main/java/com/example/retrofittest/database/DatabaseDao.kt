package com.example.retrofittest.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.retrofittest.database_tables.Passwords
import com.example.retrofittest.database_tables.Usernames

@Dao
interface DatabaseDao {

    //scheinbar bei queries kein suspend fun sondern nur fun
    // Flow statt LiveData falls man kontinuierlichen datenstrom hat statt batch weise aufrufe
    @Query("SELECT * FROM usernames ORDER BY username ASC")
    fun getAllUsernames(): LiveData<List<Usernames>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsernames(usernames: Usernames)

    @Delete
    suspend fun deleteUsernames(usernames: Usernames)




    @Query("SELECT * FROM passwords")
    fun getAllPasswords(): LiveData<List<Passwords>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPasswords(passwords: Passwords)
}