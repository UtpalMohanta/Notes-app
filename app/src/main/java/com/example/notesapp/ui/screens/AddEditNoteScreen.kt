package com.example.notesapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.notesapp.viewmodel.NoteViewModel
import com.example.notesapp.data.Note
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditNoteScreen(
    viewModel: NoteViewModel,
    noteId: Int?,
    onDone: () -> Unit
) {
    val scope = rememberCoroutineScope()

    // 👉 এই remember গুলো ফাংশনের ভিতরে থাকবে, বাইরে নয়
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    var color by remember { mutableStateOf(0xFFFFFF) }

    // noteId দিয়ে পুরোনো নোট লোড করা
    LaunchedEffect(key1 = noteId) {
        if (noteId != null) {
            val note = viewModel.getNoteById(noteId)
            note?.let {
                title = it.title
                content = it.content
                color = it.color
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (noteId == null) "Add Note" else "Edit Note") }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Title") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = content,
                onValueChange = { content = it },
                label = { Text("Content") },
                modifier = Modifier.fillMaxWidth(),
                maxLines = 6
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(onClick = {
                    if (title.isBlank() && content.isBlank()) return@Button
                    scope.launch {
                        if (noteId == null) {
                            viewModel.addNote(title, content, color)
                        } else {
                            viewModel.updateNote(
                                Note(
                                    title = title,
                                    content = content,
                                    timestamp = System.currentTimeMillis(),
                                    color = color,
                                    id = noteId
                                )
                            )
                        }
                        onDone()
                    }
                }) {
                    Text("Save")
                }

                if (noteId != null) {
                    Button(onClick = {
                        scope.launch {
                            val note = Note(
                                title = title,
                                content = content,
                                timestamp = System.currentTimeMillis(),
                                color = color,
                                id = noteId
                            )
                            viewModel.deleteNote(note)
                            onDone()
                        }
                    }) {
                        Text("Delete")
                    }
                }
            }
        }
    }
}
