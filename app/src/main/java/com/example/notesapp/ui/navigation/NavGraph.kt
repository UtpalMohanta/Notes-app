package com.example.notesapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.notesapp.ui.screens.AddEditNoteScreen
import com.example.notesapp.ui.screens.NotesListScreen
import com.example.notesapp.viewmodel.NoteViewModel

@Composable
fun AppNavHost(viewModel: NoteViewModel) {

    val navController=rememberNavController()

    NavHost(navController=navController,startDestination="notes"){
        composable("notes"){
            NotesListScreen(
                viewModel=viewModel,
                onAddNote={navController.navigate("add_edit")},
                onEditNote={id->navController.navigate("add_edit/$id")}
            )
        }
        composable(
            route="add_edit/{noteId}",
            arguments=listOf(navArgument("noteId")
            {
                type=NavType.IntType;defaultValue=-1
            })
        ){ backStackEntry ->
            val noteId=backStackEntry.arguments?.getInt("noteId")?:-1
            AddEditNoteScreen(
                viewModel=viewModel,
                noteId=if(noteId==-1) null else noteId,
                onDone={
                    navController.popBackStack()
                })
        }
        composable("add_edit"){
            AddEditNoteScreen(viewModel=viewModel,noteId=null,onDone={
                navController.popBackStack()
            })
        }
    }
}