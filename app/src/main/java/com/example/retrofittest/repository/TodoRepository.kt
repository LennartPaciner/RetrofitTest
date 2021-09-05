package com.example.retrofittest.repository

import com.example.retrofittest.api.Todo
import com.example.retrofittest.api.TodoApi
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TodoRepository(private val todoApi: TodoApi) {

    suspend fun getTodos(): Response<List<Todo>> {
        return todoApi.getTodos()
    }
}

