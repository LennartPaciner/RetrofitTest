package com.example.retrofittest.api

data class CreateTodo(
    val id: Int,
    val body: String,
    val title: String,
    val userId: Int
)