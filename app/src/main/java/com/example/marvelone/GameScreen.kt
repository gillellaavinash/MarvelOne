package com.example.marvelone

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun GameScreen(navController: NavHostController, viewModel: ImagesViewModel) {
    val random = (1..731).random()
    val brush = Brush.verticalGradient(
        colors = listOf(
            Color.Green,
            Color.hsv(270f,0.8f,0.8f)
        ),
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(brush),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Welcome to our Marvel-themed games! Test your knowledge of the Marvel Universe" +
                        " with these fun and engaging puzzles. Whether you're a seasoned fan or new to the world of superheroes, " +
                        "these games offer a chance to challenge yourself and learn more about your favorite characters. " +
                        "Dive in and see how well you know Marvel!",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 20.sp,
                color = Color.White
            )
            Button(onClick = {
                navController.navigate("main_screen")
                viewModel.getMarvel(random)
            }) {
                Text("Let's Play Game 1")
            }
            Button(onClick = { navController.navigate("quiz_screen") }) {
                Text("Let's Play Game 2")
            }
        }
    }
}