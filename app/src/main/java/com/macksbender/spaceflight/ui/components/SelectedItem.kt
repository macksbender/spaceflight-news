package com.macksbender.spaceflight.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.request.ImageRequest
import com.google.accompanist.coil.CoilImage
import com.macksbender.spaceflight.ui.theme.GoogleSans
import com.macksbender.spaceflight.ui.theme.SpaceFlightTheme

@Composable
fun SelectedItem(
    modifier: Modifier = Modifier,
    imageURL: String = "",
    title: String = "",
    summary: String = ""
) {
    Column(modifier
        .background(
            Brush.linearGradient(
                colors = listOf(Color(0xFF2E2E2E), Color(0xFF4E4E4E)),
                start = Offset(0f, 0f),
                end = Offset.Infinite
            ), RoundedCornerShape(0.dp, 0.dp, 24.dp, 24.dp)
        ), horizontalAlignment = Alignment.CenterHorizontally){
        CoilImage(
            request = ImageRequest.Builder(LocalContext.current)
                .data(imageURL)
                .build(),
            contentScale = ContentScale.FillBounds,
            fadeIn = true,
            contentDescription = "",
            loading = {
                CircularProgressIndicator(
                    color = Color.White,
                    modifier = Modifier.scale(0.1f).align(Alignment.Center)
                )
            },
            error = {
                CircularProgressIndicator(
                    color = Color.White,
                    modifier = Modifier.scale(0.2f).align(Alignment.Center)
                )
            },
            modifier = Modifier
                .height(280.dp)
                .fillMaxWidth()
        )
        Spacer(Modifier.height(18.dp))
        Text(
            text = title,
            fontSize = 14.sp,
            color = Color.White,
            fontFamily = GoogleSans,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(18.dp, 0.dp)
        )
        Spacer(Modifier.height(18.dp))
        Text(
            text = summary,
            fontSize = 12.sp,
            color = Color.LightGray,
            fontFamily = GoogleSans,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(18.dp, 0.dp)
        )
        Spacer(Modifier.height(18.dp))
        Box(
            Modifier
                .height(4.dp)
                .width(70.dp)
                .background(Color.White, CircleShape))
        Spacer(Modifier.height(18.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun SelectedItemPreview() {
    SpaceFlightTheme {
        SelectedItem(
            Modifier.fillMaxWidth(),
            title = "Crew Dragon brings four astronauts back to nighttime splashdown",
            summary = "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like)."
        )
    }
}