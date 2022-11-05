package ru.eshtykin.notesapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ru.eshtykin.notesapp.navigation.NavRoute
import ru.eshtykin.notesapp.ui.theme.NotesAppTheme

@Composable
fun AddScreen (navController: NavHostController){
    var title by remember { mutableStateOf("") }
    var subtitle by remember { mutableStateOf("") }

    Scaffold(
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Add new note",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            OutlinedTextField(
                value = title,
                onValueChange = {title = it},
                label = {Text(text = "Note title")}
            )
            OutlinedTextField(
                value = subtitle,
                onValueChange = {subtitle = it},
                label = {Text(text = "Note subtitle")}
            )
            Button(
                modifier = Modifier.padding(top = 16.dp),
                onClick = {
                    navController.navigate(NavRoute.Main.route)
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black)
            ) {
                Text(text = "Add note", color = Color.White)
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun prevAddScreen(){
    NotesAppTheme {
        AddScreen(navController = rememberNavController())
    }
}