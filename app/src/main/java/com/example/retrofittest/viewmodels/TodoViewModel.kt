package com.example.retrofittest.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.retrofittest.api.Todo
import com.example.retrofittest.repository.TodoRepository
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class TodoViewModel(private val repository: TodoRepository) : ViewModel() {

    val todoList = MutableLiveData<List<Todo>>()

    fun getTodos() = viewModelScope.launch {
        val response = repository.getTodos()
        if (response.isSuccessful && response.body() != null) {
            todoList.postValue(response.body())
        }
        else {
            Log.e("xd", "ioexpection")
        }
    }
}


//brauch das für viewmodels falls wir dem viewmodel argumente übergeben im konstruktor
class TodoViewModelFactory(private val repository: TodoRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TodoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TodoViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}