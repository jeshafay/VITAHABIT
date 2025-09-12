package com.example.vitahabit.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp, bottomEnd = 16.dp),
    large = RoundedCornerShape(16.dp)
)