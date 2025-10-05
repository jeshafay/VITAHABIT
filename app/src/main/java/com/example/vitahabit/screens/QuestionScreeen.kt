package com.example.vitahabit.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vitahabit.R
import androidx.compose.ui.text.font.FontFamily
import com.example.vitahabit.ui.theme.VitaHabitTheme

// TODO: Replace with actual Inter font family if added to resources
val Inter = FontFamily.Default

@Composable
fun WallpaperWithButton(resourceId: Int, description: String) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = resourceId),
            contentDescription = description,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 45.dp, end = 46.dp),
            contentAlignment = Alignment.BottomEnd
        ) {
            Box(contentAlignment = Alignment.Center) {
                Image(
                    painter = painterResource(id = R.drawable.rectangle_under_start),
                    contentDescription = "Next button background",
                    modifier = Modifier
                        .size(width = 130.dp, height = 32.dp),
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

@Composable fun Wallpaper1() = WallpaperWithButton(R.drawable.w1_gender, "Wallpaper 1")
@Composable fun Wallpaper2() = WallpaperWithButton(R.drawable.w2_age, "Wallpaper 2")
@Composable fun Wallpaper3() = WallpaperWithButton(R.drawable.w3_weight, "Wallpaper 3")
@Composable fun Wallpaper4() = WallpaperWithButton(R.drawable.w4_height, "Wallpaper 4")
@Composable fun Wallpaper5() = WallpaperWithButton(R.drawable.w5_main_exercising_reason, "Wallpaper 5")
@Composable fun Wallpaper6() = WallpaperWithButton(R.drawable.w6_type_of_workout, "Wallpaper 6")
@Composable fun Wallpaper7() = WallpaperWithButton(R.drawable.w7_motivation, "Wallpaper 7")
@Composable fun Wallpaper8() = WallpaperWithButton(R.drawable.w8_reminders, "Wallpaper 8")
@Composable fun Wallpaper9() = WallpaperWithButton(R.drawable.w9_time_management, "Wallpaper 9")
@Composable fun Wallpaper10() = WallpaperWithButton(R.drawable.w10_solo_or_group, "Wallpaper 10")
@Composable fun Wallpaper11() = WallpaperWithButton(R.drawable.w11_time_of_day, "Wallpaper 11")
@Composable fun Wallpaper12() = WallpaperWithButton(R.drawable.w12_check_in, "Wallpaper 12")

@Composable
fun QuestionScreen() {
    Wallpaper1()
}

@Preview(showBackground = true)
@Composable
fun QuestionScreenPreview() {
    VitaHabitTheme {
        QuestionScreen()
    }
}
