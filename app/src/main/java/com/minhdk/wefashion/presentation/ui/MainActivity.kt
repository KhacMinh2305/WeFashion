package com.minhdk.wefashion.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import com.minhdk.wefashion.infrastructure.remote.AuthenticationService
import com.minhdk.wefashion.infrastructure.remote.DataApiService
import com.minhdk.wefashion.presentation.ui.theme.WeFashionTheme
import com.minhdk.wefashion.util.helper.logD
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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

        lifecycleScope.launch(Dispatchers.IO) {

            launch {
                try {
                    val response = service.getCategories()
                    Log.d("vewsmn", "request 1 success")
                } catch (e: Exception) {
                    Log.d("vewsmn", "loi1: ${e.toString()}")
                }
            }

            launch {
                try {
                    val response = service.getCategory(5)
                    Log.d("vewsmn", "request 2 success")
                } catch (e: Exception) {
                    Log.d("vewsmn", "loi2: ${e.toString()}")
                }
            }
        }
    }
}