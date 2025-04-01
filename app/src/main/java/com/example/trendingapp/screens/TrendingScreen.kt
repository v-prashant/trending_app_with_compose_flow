package com.example.trendingapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.trendingapp.R
import com.example.trendingapp.Trending.TrendingViewModel
import com.example.trendingapp.api.UiState
import com.example.trendingapp.model.Item

@Composable
fun TrendingScreen(trendingVM: TrendingViewModel = hiltViewModel()) {

    val repositories = trendingVM.repositories.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            topBar = {
                AppBar(title = "Trending")
            },
            contentWindowInsets = WindowInsets.statusBars
        ) { innerPadding ->
            when (repositories.value) {
                is UiState.Loading -> SkeletonTrendingScreen(modifier = Modifier.padding(paddingValues = innerPadding))
                is UiState.Error -> {
                    ErrorScreen(modifier = Modifier.padding(paddingValues = innerPadding),
                        onClick = {
                            trendingVM.getRepositories()
                        })
                }
                else -> {
                    TrendingList(
                        modifier = Modifier.padding(paddingValues = innerPadding),
                        repo = (repositories.value as UiState.Success).data
                    )
                }
            }

        }
    }
}

@Composable
fun TrendingList(modifier: Modifier, repo: List<Item>) {

    LazyColumn(modifier = modifier) {
        items(repo.size) { index ->
            TrendingCard(repo[index])
        }
    }

}

@Composable
fun TrendingCard(trendingItem: Item) {
    val isExtended = rememberSaveable { mutableStateOf(trendingItem.isExpanded) }

    Card(modifier = Modifier.fillMaxWidth().padding(8.dp),
        onClick = {
            isExtended.value = !isExtended.value
            trendingItem.isExpanded = isExtended.value
        }) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(trendingItem.htmlUrl)
                    .placeholder(R.drawable.profile_user)
                    .error(R.drawable.profile_user)
                    .build(),
                contentDescription = "Profile",
                modifier = Modifier
                    .weight(0.2f)
                    .size(72.dp)
            )
            Spacer(modifier = Modifier.padding(start = 12.dp))
            Column(modifier = Modifier.weight(0.7f)) {
                Text(text = trendingItem.name ?: "",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold)
                Text(text = trendingItem.title ?: "",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium)

                if(isExtended.value){
                    Text(text = trendingItem.description ?: "")
                    Spacer(modifier = Modifier.padding(top = 10.dp))
                    Row(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween){

                        Row(modifier = Modifier.padding(start = 8.dp)) {
                            Icon(imageVector = Icons.Default.AccountCircle,
                                contentDescription = "Account"
                            )
                            Spacer(modifier = Modifier.padding(end = 2.dp))
                            Text(text = trendingItem.language ?: "Kotlin", fontSize = 20.sp)
                        }

                        Row {
                            Icon(imageVector = Icons.Default.CheckCircle,
                                contentDescription = "Account"
                            )
                            Spacer(modifier = Modifier.padding(end = 2.dp))
                            Text(text = trendingItem.watchersCount ?: "5", fontSize = 20.sp)
                        }

                        Row(modifier = Modifier.padding(end = 16.dp)) {
                            Icon(imageVector = Icons.Default.Email,
                                contentDescription = "Account"
                            )
                            Spacer(modifier = Modifier.padding(end = 2.dp))
                            Text(text = trendingItem.forksCount ?: "10", fontSize = 20.sp)
                        }

                    }
                    Spacer(modifier = Modifier.padding(top = 10.dp))
                }
            }
        }

    }
}

@Preview(showBackground = true, widthDp = 300, heightDp = 500)
@Composable
fun TrendingScreenPreview() {
    TrendingScreen()
}