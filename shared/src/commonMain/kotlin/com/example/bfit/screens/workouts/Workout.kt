package com.example.bfit.screens.workouts

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun WorkoutScreen(){
    Scaffold {
        Text("Workout")
    }
}

@Preview(showBackground = true)
@Composable
fun WorkoutScreenPreview(){
    WorkoutScreen()
}

