package com.example.retrofittest.database_tables

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "passwords")
data class Passwords(
    val password: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}