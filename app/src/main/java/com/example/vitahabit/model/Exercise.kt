// Exercise Class

package com.example.vitahabit.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Exercise(
   @StringRes val nameId: Int,
   @StringRes val descriptionId: Int,
   @DrawableRes val imageId: Int
)
