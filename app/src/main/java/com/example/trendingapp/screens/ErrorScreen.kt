package com.example.trendingapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trendingapp.R


@Composable
fun ErrorScreen(modifier: Modifier, onClick: () -> Unit) {
    Box(modifier = modifier)  {
        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(fraction = .92f),
            verticalArrangement = Arrangement.Center) {
            Image(
                painter = painterResource(R.drawable.no_internet_connection),
                contentDescription = "No Internet Connection",
                modifier = Modifier
                    .fillMaxWidth()
            )
            Text(text = "Somthing went wrong!", fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.fillMaxWidth().padding(top = 12.dp),
                textAlign = TextAlign.Center
            )
            Text(text = "An Alien is blocking your signal",
                modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
                textAlign = TextAlign.Center)
        }

        TextButton(onClick = {
            onClick()
        },
            modifier = Modifier.fillMaxWidth()
                .height(56.dp)
                .align(Alignment.BottomCenter)
        ) {
            Text(text = "Retry",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 16.sp,
                textAlign = TextAlign.Center)
        }

    }
}

@Preview(showBackground = true, widthDp = 300, heightDp = 500)
@Composable
fun ErrorScreenPreview() {
    ErrorScreen(modifier = Modifier, onClick = {})
}