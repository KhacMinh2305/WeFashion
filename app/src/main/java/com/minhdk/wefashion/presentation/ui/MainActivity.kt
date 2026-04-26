package com.minhdk.wefashion.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.minhdk.wefashion.infrastructure.remote.DataApiService
import com.minhdk.wefashion.presentation.ui.custom.GradientBorderBox
import com.minhdk.wefashion.presentation.ui.theme.WeFashionTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var service: DataApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeFashionTheme {

            }
        }

//        lifecycleScope.launch(Dispatchers.IO) {
//
//            launch {
//                try {
//                    val response = service.getCategories()
//                    Log.d("vewsmn", "request 1 success")
//                } catch (e: Exception) {
//                    Log.d("BaseAuthenticator", "loi1: ${e.toString()}")
//                }
//            }
//
//            launch {
//                try {
//                    val response = service.getCategory(5)
//                    Log.d("vewsmn", "request 2 success")
//                } catch (e: Exception) {
//                    Log.d("BaseAuthenticator", "loi2: ${e.toString()}")
//                }
//            }
//        }
    }
}