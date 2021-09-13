package com.example.retrofittest.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.example.retrofittest.database_tables.Passwords
import com.example.retrofittest.database_tables.Usernames
import com.example.retrofittest.repository.DatabaseRepository
import com.example.retrofittest.repository.TodoRepository
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class DatabaseViewModel(private val repository: DatabaseRepository) : ViewModel() {

    fun getAllUsernames(): LiveData<List<Usernames>> {
        return repository.getAllUsernames()
    }

    fun insertUsernames(usernames: Usernames) = viewModelScope.launch {
        repository.insertUsernames(usernames)
    }

    // TODO check ob auflösen auf fun mehr sinn macht oder nicht
    suspend fun checkRegisterUsername(username: String): List<Usernames> {
        return repository.checkRegisterUsername(username)
    }

    suspend fun deleteUsernamesTable() = repository.deleteUsernamesTable()

    fun getAllPasswords(): LiveData<List<Passwords>> {
        return repository.getAllPasswords()
    }

    fun insertPasswords(passwords: Passwords) = viewModelScope.launch {
        repository.insertPasswords(passwords)
    }

    suspend fun checkRegisterPassword(password: String): List<Passwords> {
       return repository.checkRegisterPassword(password)
    }

    suspend fun deletePasswordsTable() = repository.deletePasswordsTable()

}



//brauch das für viewmodels falls wir dem viewmodel argumente übergeben im konstruktor
class DatabaseViewModelFactory(private val repository: DatabaseRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DatabaseViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DatabaseViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}