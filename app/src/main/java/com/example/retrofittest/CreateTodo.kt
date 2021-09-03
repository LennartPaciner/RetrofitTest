package com.example.retrofittest

data class CreateTodo(
    val id: Int,
    val body: String,
    val title: String,
    val userId: Int
)