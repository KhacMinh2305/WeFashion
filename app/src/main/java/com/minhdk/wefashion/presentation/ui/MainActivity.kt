package com.minhdk.wefashion.presentation.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.lifecycleScope
import com.minhdk.wefashion.domain.data.RequestResult
import com.minhdk.wefashion.domain.repository.CategoryRepository
import com.minhdk.wefashion.infrastructure.remote.api.DataApiService
import com.minhdk.wefashion.presentation.ui.common.BaseButton
import com.minhdk.wefashion.presentation.ui.navigation.appNavBarItemConfig
import com.minhdk.wefashion.presentation.ui.navigation.base.AppBottomBar
import com.minhdk.wefashion.presentation.ui.navigation.navigationItems
import com.minhdk.wefashion.presentation.ui.theme.WeFashionTheme
import com.minhdk.wefashion.util.helper.logD
import dagger.hilt.android.AndroidEntryPoint
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

                var selectedPos by remember { mutableIntStateOf(0) }

                var text by remember { mutableStateOf("") }

                @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        AppBottomBar(
                            items = navigationItems,
                            itemConfig = appNavBarItemConfig(),
                            isSelected = { it == selectedPos }
                        ) { position -> selectedPos = position }
                    }
                ) { _ ->

                }
            }
        }
    }
}

//private val TURTLE_TOWER_POINT = GeoPoint(latitude = 21.027778, longitude = 105.852222)
//private const val INITIAL_CAMERA_ZOOM = 12.0
//
//@Composable
//fun MapScreen() {
//    val initialCameraOptions: InitialCameraOptions = InitialCameraOptions.LocationBased(
//        position = TURTLE_TOWER_POINT,
//        zoom = INITIAL_CAMERA_ZOOM,
//    )
//    val mapDisplayInfrastructure = MapDisplayInfrastructure(
//        sdkContext = TomTomSdk.sdkContext,
//    ) {
//        locationInfrastructure = MapLocationInfrastructure {
//            locationProvider = TomTomSdk.locationProvider
//        }
//    }
//    val mapViewState = rememberMapViewState(initialCameraOptions = initialCameraOptions)
//
//    TomTomMap(
//        state = mapViewState,
//        infrastructure = mapDisplayInfrastructure,
//    )
//}