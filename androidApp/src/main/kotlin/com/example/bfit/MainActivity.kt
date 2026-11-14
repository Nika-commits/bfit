package com.example.bfit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            App()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}
//
//@Composable
//private fun StatusBarProtection(
//    color: Color = MaterialTheme.colorScheme.surfaceContainer,
//) {
//    Spacer(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(
//                with(LocalDensity.current) {
//                    (WindowInsets.statusBars.getTop(this) * 1.2f).toDp()
//                }
//            )
//            .background(
//                brush = Brush.verticalGradient(
//                    colors = listOf(
//                        color.copy(alpha = 1f),
//                        color.copy(alpha = 0.8f),
//                        Color.Transparent
//                    )
//                )
//            )
//    )
//}