// Exercise Class

package com.example.vitahabit.data.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Exercise(
   val repsAmount: Int,
   @StringRes val nameId: Int,
   @StringRes val descriptionId: Int,
   @DrawableRes val imageId: Int
)
