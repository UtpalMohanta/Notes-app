package com.example.notesapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "notes")
data class Note(
   val title: String,
   val content: String,
   val timestamp: Long,
   val color: Int,
   @PrimaryKey(autoGenerate = true) val id: Int = 0
)