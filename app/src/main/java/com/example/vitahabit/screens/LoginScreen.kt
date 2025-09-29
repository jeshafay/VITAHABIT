package com.example.vitahabit.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.Typography
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vitahabit.R
import com.example.vitahabit.ui.theme.Inter
import com.example.vitahabit.ui.theme.VitaHabitTheme

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
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(R.drawable.wallpaper_2),
                contentDescription = "Background wallpaper image for the login screen",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
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
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)) {
                    TextField(
                        value = username,
                        onValueChange = { username = it },
                        label = { Text("Email", fontFamily = Inter, fontWeight = FontWeight.Medium, fontSize = 22.sp, color = Color.White.copy(alpha = 0.50f)) },
                        textStyle = TextStyle(fontFamily = Inter, color = Color.White.copy(alpha = 0.50f), fontSize = 22.sp, fontWeight = FontWeight.Medium),
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color.White,
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        ),
                        leadingIcon = {
                            Image(
                                painter = painterResource(R.drawable.email_line),
                                contentDescription = "Email Icon",
                                modifier = Modifier.height(5.dp).width(0.1.dp).padding(end = 8.dp),
                                contentScale = ContentScale.FillBounds
                            )
                        }
                    )
                    Spacer(modifier = Modifier.fillMaxWidth().height(2.dp).background(Color.White.copy(alpha = 0.50f)))
                }

                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 82.dp)) {
                    TextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Password", fontFamily = Inter, fontWeight = FontWeight.Medium, fontSize = 22.sp, color = Color.White.copy(alpha = 0.50f)) },
                        textStyle = TextStyle(fontFamily = Inter, color = Color.White.copy(alpha = 0.50f), fontSize = 22.sp),
                        visualTransformation = PasswordVisualTransformation(),
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color.White,
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        )
                        ,
                        leadingIcon = {
                            Image(
                                painter = painterResource(R.drawable.email_line),
                                contentDescription = "Password Icon",
                                modifier = Modifier.height(5.dp).width(5.dp).padding(end = 8.dp),
                                contentScale = ContentScale.FillBounds
                            )
                        }
                    )
                    Spacer(modifier = Modifier.fillMaxWidth().height(2.dp).background(Color.White.copy(alpha = 0.50f)))
                }

                if (showError) {
                    Text(
                        text = "Email atau password salah",
                        color = Color.Red,
                        modifier = Modifier.padding(bottom = 7.dp)
                    )
                }
                Button(
                    onClick = {
                        if (username == "hidupjokowi" && password == "TIF123") {
                            onLoginClick()
                        } else {
                            showError = true
                        }
                    },
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(bottom = 80.dp)
                        .height(32.dp)
                        .width(130.dp)


                ) {
                    Text("")
                }
            }
            // Box for the "Next" button with background image
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 80.dp, end = 16.dp),
                contentAlignment = Alignment.BottomEnd
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Image(
                        painter = painterResource(id = R.drawable.rectangle_under_start),
                        contentDescription = "Next button background",
                        modifier = Modifier
                            .size(width = 130.dp, height = 32.dp), // Fixed size for the button background
                        contentScale = ContentScale.FillBounds
                    )
                    Text(
                        text = "Next",
                        color = Color.Black,
                        fontFamily = Inter,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(end = 40.dp)
                    )
                }
                // Iconify arrow
                Image(
                    painter = painterResource(id = R.drawable.iconify_arrow),
                    contentDescription = "Next arrow",
                    modifier = Modifier
                        .padding(bottom = 3.dp, end = 16.dp)
                        .size(24.dp)
                        .align(Alignment.BottomEnd),
                    contentScale = ContentScale.Fit
                )
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
