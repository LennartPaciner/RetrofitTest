package com.example.retrofittest


//mit Plugin kotlin class aus json erstellt -> brauchen kotlin klasse die die attribute aus dem json ergebnis darstellt

data class Todo(
    val completed: Boolean,
    val id: Int,
    val title: String,
    val userId: Int
)