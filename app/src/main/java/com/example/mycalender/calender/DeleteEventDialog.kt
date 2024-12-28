package com.example.mycalender.calender

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DeleteEventDialog(
    eventTitle: String,
    onConfirmDelete: () -> Unit,
    onCancel: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onCancel,
        confirmButton = {
            Button(onClick = onConfirmDelete) {
                Text(text = "Delete")
            }
        },
        dismissButton = {
            OutlinedButton(onClick = onCancel) {
                Text(text = "Cancel")
            }
        },
        title = {
            Text(text = "Delete Itinerary")
        },
        text = {
            Text(text = "Are you sure you want to delete the itinerary: \"$eventTitle\"?")
        },
        modifier = Modifier.padding(16.dp)
    )
}
