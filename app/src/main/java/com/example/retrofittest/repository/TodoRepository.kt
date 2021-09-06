package com.example.retrofittest.repository

import com.example.retrofittest.api.Todo
import com.example.retrofittest.api.TodoApi
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class TodoRepository(private val todoApi: TodoApi) {

    // TODO not yet sure wofür man repo wirklich braucht -> mittels tutorial theorie verstehen
    suspend fun getTodos(): Response<List<Todo>> {
        return todoApi.getTodos()
    }

    suspend fun deleteTodo(value: Int): Response<Void> {
        return todoApi.deleteTodo(value)
    }

}

