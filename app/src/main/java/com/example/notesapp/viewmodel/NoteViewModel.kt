package com.example.notesapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.data.Note
import com.example.notesapp.data.NoteDatabase
import com.example.notesapp.repository.NoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val repo: NoteRepository
    private val _searchQuery = MutableStateFlow("")


    init {
        val dao = NoteDatabase.getDatabase(application).noteDao()
        repo = NoteRepository(dao)
    }


    val searchQuery: StateFlow<String> = _searchQuery


    fun setSearchQuery(q: String) { _searchQuery.value = q }


    // combine search query with notes flow
    val notes = _searchQuery.debounce(200)
        .flatMapLatest { query ->
            if (query.isBlank()) repo.getNotes()
            else repo.searchNotes(query)
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())


    fun addNote(title: String, content: String, color: Int) = viewModelScope.launch {
        val note = Note(
            title = title,
            content = content,
            timestamp = System.currentTimeMillis(),
            color = color
        )
        repo.insert(note)
    }


    fun updateNote(note: Note) = viewModelScope.launch { repo.update(note) }
    fun deleteNote(note: Note) = viewModelScope.launch { repo.delete(note) }


    suspend fun getNoteById(id: Int) = repo.getNoteById(id)
}