package com.macksbender.spaceflight.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import com.macksbender.spaceflight.MainViewModel
import com.macksbender.spaceflight.ui.components.HistoryItem
import com.macksbender.spaceflight.ui.components.SelectedItem
import com.macksbender.spaceflight.ui.theme.GoogleSans
import com.macksbender.spaceflight.ui.theme.SpaceFlightTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@ExperimentalAnimationApi
@Composable
fun MainScreen(
    navController: NavController? = null,
    previewMode: Boolean = false,
    viewModel: MainViewModel = hiltNavGraphViewModel()
) {

    var newsList by remember { viewModel.newsList }
    var isLoading by remember { viewModel.isLoading }
    var lastError by remember { viewModel.lastError }

    var selectedArticle by remember { mutableStateOf(0) }

    var selectionVisibility by remember { mutableStateOf(previewMode) }
    var historyVisibility by remember { mutableStateOf(previewMode) }

    val listState = rememberLazyListState()
    // Remember a CoroutineScope to be able to launch
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = selectionVisibility) {
        delay(1000)
        selectionVisibility = true
        delay(100)
        historyVisibility = true
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
        LazyColumn(state = listState) {
            item {
                AnimatedVisibility(
                    visible = selectionVisibility && newsList.isNotEmpty(),
                    enter = slideInVertically(
                        initialOffsetY = { -2000 },
                        animationSpec = tween(durationMillis = 200)
                    ),
                    exit = slideOutVertically(
                        animationSpec = tween(durationMillis = 200)
                    )
                ) {
                    SelectedItem(
                        Modifier.fillMaxWidth(),
                        imageURL = newsList[selectedArticle].imageUrl,
                        title = newsList[selectedArticle].title,
                        summary = newsList[selectedArticle].summary
                    )
                }
            }
            item {
                AnimatedVisibility(
                    visible = historyVisibility && newsList.isNotEmpty(),
                    enter = slideInVertically(
                        initialOffsetY = { 2000 },
                        animationSpec = tween(durationMillis = 200)
                    ),
                    exit = slideOutVertically(
                        animationSpec = tween(durationMillis = 200)
                    )
                ) {
                    Column(Modifier.padding(20.dp, 0.dp)) {
                        Spacer(Modifier.height(28.dp))
                        Text(
                            text = "History",
                            fontSize = 24.sp,
                            color = Color.White,
                            fontFamily = GoogleSans,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
            items(newsList.size) {
                AnimatedVisibility(
                    visible = historyVisibility,
                    enter = slideInVertically(
                        initialOffsetY = { 2000 },
                        animationSpec = tween(durationMillis = 200)
                    ),
                    exit = slideOutVertically(
                        animationSpec = tween(durationMillis = 200)
                    )
                ) {
                    HistoryItem(
                        Modifier
                            .fillMaxWidth()
                            .padding(20.dp, 6.dp),
                        title = newsList[it].title,
                        onReadRequest = {
                            selectedArticle = it
                            coroutineScope.launch {
                                // Animate scroll to the 10th item
                                listState.animateScrollToItem(index = 0)
                            }
                        }
                    )
                }
            }
        }
    }
}

@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    SpaceFlightTheme {
        MainScreen(null, true)
    }
}