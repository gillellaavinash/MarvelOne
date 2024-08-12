package com.example.marvelone

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry

@Composable
fun Score(backStackEntry: NavBackStackEntry) {
    val score = backStackEntry.arguments?.getInt("score")
    val score1 = score?.toFloat()
    val total = 5F
    val brush = Brush.verticalGradient(
        colors = listOf(
            Color.LightGray,
            Color.DarkGray
        ),
    )
    Box(
        modifier = Modifier
            .background(brush)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column(
            verticalArrangement = Arrangement.spacedBy(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
                Text(
                    text = "Congratulations you have done a great job \uD83C\uDF89\uD83C\uDF89",
                    modifier = Modifier.padding(16.dp),
                    color = Color.White,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )
                CircularProgressIndicator(
                    progress = (score1!!/ total), // Set a fixed progress value between 0.0 and 1.0
                    strokeWidth = 12.dp, // Optional: Customize the stroke width
                    modifier = Modifier.size(180.dp),
                    color = Color.Green,
                    trackColor = Color.Red
                )
                Text(
                    text = "$score1/$total",
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center,
                )
        }
    }
}