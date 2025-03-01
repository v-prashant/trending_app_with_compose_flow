package com.example.trendingapp.Trending

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.trendingapp.screens.TrendingScreen
import com.example.trendingapp.ui.theme.TrendingAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TrendingActivity : ComponentActivity() {

    @Inject
    lateinit var trendingViewModel: TrendingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            App()
        }
    }

    @Composable
    fun App() {
        TrendingAppTheme {
            TrendingScreen(trendingViewModel)
        }
    }

    @Preview(showBackground = true, widthDp = 300, heightDp = 500)
    @Composable
    fun AppPreview() {
        App()
    }

}

