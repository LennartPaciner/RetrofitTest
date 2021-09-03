package com.example.retrofittest

import retrofit2.Response
import retrofit2.http.*

// interface um funktionen für die api zu haben z.B. bekomme alle todos (todo = data class und json struktur)
interface TodoApi {

    @GET("/todos")
    suspend fun getTodos(): Response<List<Todo>>

    //fun getTodos(@Query("key") key: String): Response<List<Todo>>  // falls man api key für abfragen braucht

    @POST("/posts")
    suspend fun createTodo(@Body todo: CreateTodo): Response<CreateTodo>

    //put tauscht komplettes objekt aus, patch updatet nur spezifisches feld
    @PUT("/posts/{id}")
    suspend fun putTodo(@Path("id") id: Int, @Body todo: CreateTodo): Response<CreateTodo>

    @DELETE("/posts/{id}")
    suspend fun deleteTodo(@Path("id") id: Int) : Response<Void>
}