package com.example.bfit.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay

@Composable
fun AppNavigationPrimary(){
    val backstack = rememberNavBackStack(navigationConfig)
//
//    NavDisplay(
//        backstack = backstack,
//
//    )
}