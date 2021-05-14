package com.macksbender.spaceflight.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.macksbender.spaceflight.ui.theme.GoogleSans
import com.macksbender.spaceflight.ui.theme.SpaceFlightTheme

@Composable
fun HistoryItem(
    modifier: Modifier = Modifier,
    title: String = "Unknown",
    onReadRequest: () -> Unit = {}
    ) {
    Row(modifier
        .background(
            Brush.linearGradient(
                colors = listOf(Color(0xFF2E2E2E), Color(0xFF4E4E4E)),
                start = Offset(0f, 0f),
                end = Offset.Infinite
            ), RoundedCornerShape(25))
        .clip(RoundedCornerShape(25))
        .clickable {
            onReadRequest()
        }
        .padding(16.dp, 32.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontSize = 13.sp,
            color = Color.White,
            fontFamily = GoogleSans,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.weight(1f)
        )
        Box(Modifier
            .background(Color.White, CircleShape)
            .padding(24.dp, 6.dp)
        ) {
            Text(
                text = "Read",
                fontSize = 13.sp,
                color = Color(0xFF263238),
                fontFamily = GoogleSans,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HistoryItemPreview() {
    SpaceFlightTheme {
        HistoryItem(
            Modifier.fillMaxWidth(),
            title = "SpaceX test fires a Falcon 9 rocket ahead of Starlink Mission"
        )
    }
}