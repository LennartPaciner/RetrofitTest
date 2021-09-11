package com.example.retrofittest.repository

import com.example.retrofittest.api.CreateTodo
import com.example.retrofittest.api.Todo
import com.example.retrofittest.api.TodoApi
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class TodoRepository(private val todoApi: TodoApi) {

    suspend fun getTodos(): Response<List<Todo>> {
        return todoApi.getTodos()
    }

    suspend fun createTodo(todo: CreateTodo): Response<CreateTodo> {
        return todoApi.createTodo(todo)
    }

    suspend fun updateTodo(value: Int, todo: CreateTodo): Response<CreateTodo> {
        return todoApi.putTodo(value, todo)
    }

    suspend fun deleteTodo(value: Int): Response<Void> {
        return todoApi.deleteTodo(value)
    }

}

