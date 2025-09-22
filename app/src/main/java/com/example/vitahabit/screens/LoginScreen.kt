package com.example.vitahabit.screens

import androidx.compose.foundation.Image
import com.example.vitahabit.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vitahabit.ui.theme.VitaHabitTheme

//@Composable
//fun LoginScreen() {
//    Box(
//        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center
//    ) {
//        Text("Login Screen")
//    }
//}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(onLoginClick: () -> Unit) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        // Consider how these images should be layered or positioned.
        // Box is often useful for layering.
        Box(modifier = Modifier.fillMaxSize()) { // Use Box for layering if needed
            Image(
                painter = painterResource(R.drawable.wallpaper_2),
                contentDescription = "Background wallpaper image for the login screen",
                modifier = Modifier.fillMaxSize(), // Example: Make wallpaper fill the screen
                contentScale = ContentScale.Crop // Often used with fillMaxSize for images
            )
            // Rectangle at the bottom
            Image(
                painter = painterResource(R.drawable.rectangle_1),
                contentDescription = "Decorative rectangle element",
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )
            Column(
                modifier = Modifier
                    .fillMaxSize() // Make column take full space to center its content
                    .padding(horizontal = 16.dp), // Add some padding around the content
                verticalArrangement = Arrangement.Bottom, // Align content to the bottom
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text("Username", fontSize = 12.sp) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp), // Increased bottom padding
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    )
                )
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password", fontSize = 12.sp) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp), // Increased bottom padding
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent, // Make TextField background transparent
                        unfocusedContainerColor = Color.Transparent, // Make TextField background transparent when unfocused
                        disabledContainerColor = Color.Transparent, // Make TextField background transparent when disabled
                        focusedIndicatorColor = Color.Transparent, // Optional: Hide indicator when focused
                        unfocusedIndicatorColor = Color.Transparent, // Optional: Hide indicator when unfocused
                        disabledIndicatorColor = Color.Transparent // Optional: Hide indicator when disabled
                    )
                )
                if (showError) {
                    Text(
                        text = "Username atau password salah",
                        color = Color.Red,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
                Button(
                    onClick = {
                        if (username == "hidup jokowi" && password == "TIF123") {
                            onLoginClick()
                        } else {
                            showError = true
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 80.dp) // Adjust this padding to move the button further down
                ) {
                    Text("Log In")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    VitaHabitTheme {
        LoginScreen(onLoginClick = {})
    }
}
