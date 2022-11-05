package ru.eshtykin.notesapp.screens

import android.app.Application
import android.icu.text.CaseMap.Title
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ru.eshtykin.notesapp.MainViewModel
import ru.eshtykin.notesapp.MainViewModelFactory
import ru.eshtykin.notesapp.model.Note
import ru.eshtykin.notesapp.navigation.NavRoute
import ru.eshtykin.notesapp.ui.theme.NotesAppTheme

@Composable
fun MainScreen (navController: NavHostController){

    val context = LocalContext.current
    val mViewModel : MainViewModel =
        viewModel(factory = MainViewModelFactory(context.applicationContext as Application))

    val notes = mViewModel.readTest.observeAsState(listOf()).value

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                backgroundColor = Color.DarkGray,
                onClick = {
                    navController.navigate(NavRoute.Add.route)
                }
            ){
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add Icons",
                    tint = Color.White
                )
            }
        }
    ) {
//        Column(
//        ) {
//            NoteTitle(title = "Title 1", subtitle = "Subtitle 1", navController = navController)
//            NoteTitle(title = "Title 2", subtitle = "Subtitle 2", navController = navController)
//            NoteTitle(title = "Title 3", subtitle = "Subtitle 3", navController = navController)
//            NoteTitle(title = "Title 4", subtitle = "Subtitle 4", navController = navController)
//        }
        LazyColumn{
            items(notes){note ->
                NoteItem(note = note, navController = navController)
            }
        }
    }
}

@Composable
fun NoteItem(note: Note, navController: NavHostController){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 24.dp)
            .clickable {
                navController.navigate(NavRoute.Note.route)
            },
        elevation = 6.dp
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 8.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = note.title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = note.subtitle
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun prevMainScreen(){
    NotesAppTheme {
        MainScreen(navController = rememberNavController())

    }
}