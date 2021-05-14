package com.macksbender.spaceflight.screen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import com.macksbender.spaceflight.ui.theme.GoogleSans
import com.macksbender.spaceflight.ui.theme.SpaceFlightTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController? = null
) {
    var alphaState by remember { mutableStateOf(0f) }
    val alpha by animateFloatAsState(
        targetValue = alphaState,
        tween(600)
    )
    LaunchedEffect(key1 = navController) {
        delay(700)
        alphaState = 1f
        delay(1000)
        alphaState = 0f
        delay(700)
        if (navController != null) {
            navController.popBackStack()
            navController.navigate("main")
        }
    }

    Box(
        Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    colors = listOf(Color(0xFF000000), Color(0xFF343434)),
                    start = Offset(0f, 0f),
                    end = Offset.Infinite
                )
            )) {
        Text(
            fontSize = 24.sp,
            color = Color.White,
            text = ".spaceflight",
            fontFamily = GoogleSans,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Center).alpha(alpha)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SpaceFlightTheme {
        SplashScreen(null)
    }
}