package com.example.marvelone

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen(navController: NavHostController, viewModel: ImagesViewModel) {
    val brush = Brush.verticalGradient(
        colors = listOf(
            Color.Green,
            Color.hsv(270f,0.8f,0.8f)
        ),
    )
    val list = List
    var i by remember {
        mutableIntStateOf((0..19).random())
    }
    var name by remember {
        mutableStateOf("")
    }
    var borderColor by remember {
       mutableStateOf(Color.White)
    }
    var score by remember {
        mutableIntStateOf(0)
    }
    var guess by remember {
        mutableStateOf(false)
    }
    var count by remember {
        mutableIntStateOf(0)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = brush),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Guess the Character \uD83E\uDD14 ?",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 20.sp,
                color = Color.White,textAlign = TextAlign.Center
            )
            if(count<4) {
                Text(
                    text = list[i].question,
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 20.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
                OutlinedTextField(
                    value = name,
                    onValueChange = {
                        name = it
                    },
                    label = {
                        Text(text = "Enter name of the character")
                    },
                    placeholder = {
                        Text(text = "Enter name of the character")
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = borderColor,
                        unfocusedBorderColor = borderColor
                    )
                )
                if(borderColor != Color.White) {
                    Text(
                        text = "The correct answer is ${list[i].answer}",
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 20.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }
                Row(
                    modifier = Modifier.padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(46.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(onClick = {
                        guess = name.equals(list[i].answer.trim(), ignoreCase = true)
                        if (guess) {
                            borderColor = Color.Green
                            score++
                        } else {
                            borderColor = Color.Red
                        }
                    }) {
                        Text(text = "Check")
                    }
                    Button(onClick = {
                        i = (0..19).random()
                        name = ""
                        borderColor = Color.White
                        count++
                    }) {
                        Text(text = "Next")
                    }
                }
            }
            else{
                Text(
                    text = list[i].question,
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 20.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
                OutlinedTextField(
                        value = name,
                        onValueChange = {
                            name = it
                        },
                        label = {
                            Text(text = "Enter name of the character")
                        },
                        placeholder = {
                            Text(text = "Enter name of the character")
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = borderColor,
                            unfocusedBorderColor = borderColor
                        )
                )
                if(borderColor != Color.White) {
                    Text(
                        text = "The correct answer is ${list[i].answer}",
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 20.sp,
                        color = Color.White
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(46.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(onClick = {
                        guess = name.equals(list[i].answer.trim(), ignoreCase = true)
                        if (guess) {
                            borderColor = Color.Green
                            score++
                        } else {
                            borderColor = Color.Red
                        }
                    }) {
                        Text(text = "Check")
                    }
                    Button(onClick = {
                        navController.navigate("score_screen/$score")
                    }) {
                        Text(text = "Finish")
                    }
                }
            }
        }
    }
}