package com.example.vitahabit.screens.progress

import coil.compose.AsyncImage // <-- Add this import
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddAPhoto
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel

class PhotoViewModel : ViewModel() {
    // Holds the URI for the 'Before' photo
    var beforeImageUri by mutableStateOf<Uri?>(null)
    var afterImageUri by mutableStateOf<Uri?>(null)
}



@Composable
fun PhotoPlaceholder(
    label: String,
    date: String,
    weight: String,
    imageUri: Uri?, // <-- Add this parameter
    onClick: () -> Unit, // <-- Add this to make it clickable
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .clickable(onClick = onClick), // <-- Make the box clickable
            contentAlignment = Alignment.Center
        ) {
            if (imageUri != null) {
                // If an image is selected, display it
                AsyncImage(
                    model = imageUri,
                    contentDescription = label,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            } else {
                // Otherwise, show the placeholder icon
                Icon(
                    imageVector = Icons.Outlined.AddAPhoto,
                    contentDescription = label,
                    modifier = Modifier.size(48.dp),
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        }
        Text(text = label, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(vertical = 4.dp))
        Text(text = date, style = MaterialTheme.typography.bodySmall)
        Text(text = weight, style = MaterialTheme.typography.bodySmall)
    }
}