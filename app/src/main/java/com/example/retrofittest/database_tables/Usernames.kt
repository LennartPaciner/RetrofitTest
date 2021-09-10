package com.example.retrofittest.database_tables

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usernames")
data class Usernames (
    val username: String
    ) {
        @PrimaryKey(autoGenerate = true)
        var id: Int? = null
}