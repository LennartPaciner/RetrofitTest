package com.example.retrofittest.repository

import androidx.lifecycle.LiveData
import com.example.retrofittest.database.DatabaseDao
import com.example.retrofittest.database_tables.Passwords
import com.example.retrofittest.database_tables.Usernames

class DatabaseRepository(private val databaseDao: DatabaseDao) {

    fun getAllUsernames(): LiveData<List<Usernames>> {
        return databaseDao.getAllUsernames()
    }

    suspend fun insertUsernames(usernames: Usernames) {
        databaseDao.insertUsernames(usernames)
    }

    suspend fun checkRegisterUsername(username: String) : List<Usernames> {
        return databaseDao.checkRegisterUsername(username)
    }

    fun getAllPasswords(): LiveData<List<Passwords>> {
        return databaseDao.getAllPasswords()
    }

    suspend fun insertPasswords(passwords: Passwords) {
        databaseDao.insertPasswords(passwords)
    }

    suspend fun checkRegisterPassword(password: String) : List<Passwords> {
        return databaseDao.checkRegisterPassword(password)
    }
}