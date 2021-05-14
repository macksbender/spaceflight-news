package com.macksbender.spaceflight

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.macksbender.spaceflight.screen.MainScreen
import com.macksbender.spaceflight.screen.SplashScreen
import com.macksbender.spaceflight.ui.theme.SpaceFlightTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpaceFlightTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "splash"
                ) {
                    composable("splash") {
                        SplashScreen(navController)
                    }
                    composable(
                        "main",
                        arguments = listOf(
                            navArgument("present_article_id") {
                                type = NavType.IntType
                            }
                        )
                    ) {
                        MainScreen(navController)
                    }
                }
            }
        }
    }
}

