package com.example.vitahabit.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vitahabit.R
import com.example.vitahabit.ui.theme.VitaHabitTheme

val Inter = FontFamily(
    Font(R.font.inter_regular, FontWeight.Normal),
    Font(R.font.inter_medium, FontWeight.Medium),
    Font(R.font.inter_semi_bold, FontWeight.SemiBold),
    Font(R.font.inter_bold, FontWeight.Bold)
)

@Composable
fun QuestionnaireScreen(
    onQuestionnaireComplete: () -> Unit
) {
    var currentQuestionIndex by remember { mutableIntStateOf(0) }

    val wallpapers = remember {
        listOf(
            R.drawable.w1_gender, R.drawable.w2_age, R.drawable.w3_weight,
            R.drawable.w4_height, R.drawable.w5_main_exercising_reason, R.drawable.w6_type_of_workout,
            R.drawable.w7_motivation, R.drawable.w8_reminders, R.drawable.w9_time_management,
            R.drawable.w10_solo_or_group, R.drawable.w11_time_of_day, R.drawable.w12_check_in
        )
    }

    WallpaperWithButton(
        resourceId = wallpapers[currentQuestionIndex],
        description = "Latar belakang kuesioner ${currentQuestionIndex + 1}",
        onNextClick = {
            if (currentQuestionIndex < wallpapers.size - 1) {
                currentQuestionIndex++
            } else {
                onQuestionnaireComplete()
            }
        }
    )
}

@Composable
fun WallpaperWithButton(
    resourceId: Int,
    description: String,
    onNextClick: () -> Unit
) {
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
                .padding(bottom = 5.dp, end = 30.dp),
            contentAlignment = Alignment.BottomEnd
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.clickable(onClick = onNextClick)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.rectangle_under_start),
                    contentDescription = "Latar belakang tombol Next",
                    modifier = Modifier.size(width = 130.dp, height = 32.dp),
                    contentScale = ContentScale.FillBounds
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.size(width = 130.dp, height = 32.dp)
                ) {
                    Text(
                        text = "Next",
                        color = Color.Black,
                        fontFamily = Inter,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                    )
                    Image(
                        painter = painterResource(id = R.drawable.iconify_arrow),
                        contentDescription = "Panah Next",
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .size(24.dp),
                        contentScale = ContentScale.Fit
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuestionnaireScreenPreview() {
    VitaHabitTheme {
        QuestionnaireScreen(onQuestionnaireComplete = {})
    }
}