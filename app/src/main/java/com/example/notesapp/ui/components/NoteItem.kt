package com.example.notesapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notesapp.data.Note
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Composable
fun NoteItem(note:Note,onClick:()->Unit,onDelete:()->Unit){
    Card(modifier=Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .clickable{ onClick()},
        elevation=CardDefaults.cardElevation(4.dp)
    ){
        Column(modifier=Modifier.padding(12.dp)){
            Text(text = note.title)

            Spacer(modifier = Modifier.height(4.dp))

            Text(text = note.content, maxLines = 2)

            Spacer(modifier = Modifier.height(6.dp))

            val sdf = SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault())
            Text(text = sdf.format(Date(note.timestamp)))
        }
    }
}