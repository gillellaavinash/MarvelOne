package com.example.marvelone

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.marvel.api.Marvel
import com.example.marvel.api.NetworkResponse

@Composable
fun MainScreen(navController: NavHostController, viewModel: ImagesViewModel) {


    //println("getMarvel mainScreen called with accessToken: $accessToken and random: $random")

    //viewModel.getMarvel(accessToken,random)
    val images = viewModel.marvelResult.observeAsState()
    when (val result = images.value) {
            is NetworkResponse.Error -> {
                Text(text = result.message)
            }

            is NetworkResponse.Success -> {
                //Text(text = "${result.data}")
                ImageCard(data = result.data,navController,viewModel)
            }

            is NetworkResponse.Loading -> {
                //CircularProgressIndicator()
                Text("Loading",
                    modifier = Modifier
                        .padding(150.dp),
                    style = androidx.compose.ui.text.TextStyle(
                        color = Color.Black,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center
                    )
                )
                //Text("getMarvel called with accessToken: $accessToken and random: $random")
                //Text(text = "${result.data}")
            }

            null -> {
                //Text(text = "Enter City")
            }

    }
//    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageCard(data: Marvel, navController: NavHostController, viewModel: ImagesViewModel) {

    val context = LocalContext.current
    var name by remember {
        mutableStateOf("")
    }
    var guess by remember {
        mutableStateOf(false)
    }
    var borderColor by remember {
        mutableStateOf(Color.Black)
    }

    var pointerOffset by remember {
        mutableStateOf(Offset(0f, 0f))
    }
    var blur by remember{
        mutableStateOf(40.dp)
    }
    var count by remember {
        mutableIntStateOf(0)
    }
    var score by remember {
        mutableIntStateOf(0)
    }
    val brush = Brush.verticalGradient(
        colors = listOf(
            Color.LightGray,
            Color.DarkGray
        ),
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush),
    ) {
        //Lazy
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(45.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
                    .pointerInput("dragging") {
                        detectDragGestures { change, dragAmount ->
                            pointerOffset += dragAmount
                            blur = 0.dp
                        }
                    }
                    .onSizeChanged {
                        pointerOffset = Offset(it.width / 2f, it.height / 2f)
                    }
                    .drawWithContent {
                        drawContent()
                        // draws a fully black area with a small keyhole at pointerOffset that’ll show part of the UI.
                        drawRect(
                            Brush.radialGradient(
                                listOf(Color.Transparent, Color.Black),
                                center = pointerOffset,
                                radius = 65.dp.toPx(),
                            )
                        )
                    }
                    .size(400.dp)
            )
            {
                AsyncImage(
                    model = data.image.url,
                    contentDescription = "Image description",
                    modifier = Modifier
                        .size(320.dp)
                        .aspectRatio(1f)
                        .padding(30.dp)
                        .clip(
                            androidx.compose.ui.graphics.RectangleShape
                        ),
                    contentScale = androidx.compose.ui.layout.ContentScale.Crop
                )
            }

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
            if(borderColor != Color.Black) {
                Text(
                    text = "The correct answer is ${data.name}",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 20.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }

            Button(onClick = {
                count += 1
                guess = name.equals(data.name.trim(), ignoreCase = true)
                if (guess) {
                    borderColor = Color.Green
                    score++
                } else {
                    borderColor = Color.Red
                    Toast.makeText(context, "The answer is ${data.name}", Toast.LENGTH_SHORT).show()
                    //score++
                }
            }) {
                Text(text = "Submit")
            }

        }
    }
}