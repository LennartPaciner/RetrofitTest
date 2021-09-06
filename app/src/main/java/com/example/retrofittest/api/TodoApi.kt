package com.example.retrofittest.api

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
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

    //Instanz von todoapi -> aufrufen in fragments um repository zu übergeben
    companion object {

        var todoApi: TodoApi?= null

        fun getInstance(): TodoApi {
            if (todoApi == null) {
                val retrofit = Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                todoApi = retrofit.create(TodoApi::class.java)
            }

            return todoApi!!
        }

    }
}