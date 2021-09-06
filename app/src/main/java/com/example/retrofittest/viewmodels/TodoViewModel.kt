package com.example.retrofittest.viewmodels

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.retrofittest.api.CreateTodo
import com.example.retrofittest.api.Todo
import com.example.retrofittest.repository.TodoRepository
import com.google.gson.Gson
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response
import java.lang.IllegalArgumentException

class TodoViewModel(private val repository: TodoRepository) : ViewModel() {

    //LiveData is a data holder class that can be observed within a given lifecycle.
    //This means that an Observer can be added in a pair with a LifecycleOwner -> in getfragment passiert das
    val todoList = MutableLiveData<List<Todo>>()
    val todoCreate = MutableLiveData<String>()
    val todoUpdate = MutableLiveData<String>()
    val todoDelete = MutableLiveData<Response<Void>>()

    fun getTodos() = viewModelScope.launch {
        val response = try {
            repository.getTodos()
        } catch (e: HttpException) {
            Log.e("Error", e.message())
            return@launch
        }

        if (response.isSuccessful && response.body() != null) {
            todoList.postValue(response.body())
        }
        else {
            Log.e("xd", "ioexpection")
        }
    }

    fun updateTodo(value: Int, todo: CreateTodo) = viewModelScope.launch {
        val response = try {
            repository.updateTodo(value, todo)
        } catch (e: HttpException) {
            Log.e("Error", e.message())
            return@launch
        }

        if (response.isSuccessful) todoUpdate.postValue(Gson().toJson(response.body()))
    }

    fun createTodo(todo: CreateTodo) = viewModelScope.launch {
        val response = try {
            repository.createTodo(todo)
        } catch (e: HttpException) {
            Log.e("Error", e.message())
            return@launch
        }

        if (response.isSuccessful) todoCreate.postValue(Gson().toJson(response.body()))
    }

    fun deleteTodo(value: Int) = viewModelScope.launch {
        val response = try {
            repository.deleteTodo(value)
        } catch (e: HttpException) {
            Log.e("Error", e.message())
            return@launch
        }

        //packe result in mutablelivedata objekt um im fragment an das value zu kommen
        if (response.isSuccessful) todoDelete.postValue(response)
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