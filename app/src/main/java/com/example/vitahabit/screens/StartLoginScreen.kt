package com.example.vitahabit.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.vitahabit.R
import com.example.vitahabit.ui.theme.VitaHabitTheme
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vitahabit.ui.theme.Inter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartLoginScreen(onLoginClick: () -> Unit) {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(R.drawable.wallpaper),
                contentDescription = "Background wallpaper image for Start Login Screen",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Image(
                painter = painterResource(id = R.drawable.iconify_barbel),
                contentDescription = "Barbell Icon",
                modifier = Modifier.align(Alignment.Center).padding(bottom = 72.dp).size(200.dp)
            )

            Text(
                text = "VITAHABIT",
                style = MaterialTheme.typography.headlineLarge.copy(
                    letterSpacing = 6.sp
                ), color = Color.White,
                modifier = Modifier.align(Alignment.Center).padding(bottom = 280.dp)
            )

            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 220.dp), // Adjust padding as needed
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Build Strength, Build",
                    color = Color.White,
                    fontFamily = Inter,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
                Text(text = "Habits, Build You", color = Color.White, fontFamily = Inter, fontWeight = FontWeight.Bold, fontSize = 20.sp, textAlign = TextAlign.Center)
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.line_under_quote),
                    contentDescription = "Linete under quote",
                    modifier = Modifier
                        .width(300.dp)
                        .height(50.dp)
                )
                Button(
                    onClick = onLoginClick,
                    modifier = Modifier
                        .padding(bottom = 96.dp)
                        .size(width = 265.dp, height = 55.dp),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.rectangle_under_start2_),
                            contentDescription = "Next button background",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.FillBounds
                        )
                        Row (
                            modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "START",
                                color = Color.Black,
                                fontFamily = Inter,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 25.sp,
                                modifier = Modifier.padding(end = 8.dp)
                            )
                            Image(
                                painter = painterResource(id = R.drawable.iconify_arrow),
                                contentDescription = "Next arrow",
                                modifier = Modifier
                                    .size(64.dp).padding(start = 40.dp)
                            )
                        }
                    }
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun StartLoginPreview() {
    VitaHabitTheme {
        StartLoginScreen(onLoginClick = {})
    }
}