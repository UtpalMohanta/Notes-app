package com.example.notesapp.repository

import com.example.notesapp.data.Note
import com.example.notesapp.data.NoteDao
import kotlinx.coroutines.flow.Flow

class NoteRepository(private val dao: NoteDao) {
    fun getNotes(): Flow<List<Note>> = dao.getNotes()
    fun searchNotes(query: String): Flow<List<Note>> = dao.searchNotes(query)
    suspend fun insert(note: Note) = dao.insert(note)
    suspend fun update(note: Note) = dao.update(note)
    suspend fun delete(note: Note) = dao.delete(note)
    suspend fun getNoteById(id: Int) = dao.getNoteById(id)
}