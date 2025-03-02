package com.example.trendingapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SkeletonTrendingScreen(modifier: Modifier) {

    LazyColumn(modifier = modifier) {
        items(5) {
            SkeletonTrendingList()
        }
    }

}

@Composable
fun SkeletonTrendingList() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(16.dp)) {
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .clip(CircleShape)
                    .background(color = Color.Gray.copy(alpha = 0.3f))

            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Box(
                    modifier = Modifier
                        .height(20.dp)
                        .fillMaxWidth(0.6f)
                        .clip(RoundedCornerShape(4.dp))
                        .background(color = Color.Gray.copy(alpha = 0.3f))
                )
                Spacer(modifier = Modifier.height(8.dp))
                Box(
                    modifier = Modifier
                        .height(16.dp)
                        .fillMaxWidth(0.8f)
                        .clip(RoundedCornerShape(4.dp))
                        .background(color = Color.Gray.copy(alpha = 0.3f))
                )
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 300, heightDp = 500)
@Composable
fun SkeletonTrendingScreenPreview() {
    SkeletonTrendingScreen(modifier = Modifier)
}
