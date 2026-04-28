package com.minhdk.wefashion.presentation.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalActivity
import androidx.compose.material.icons.filled.Preview
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.LineHeightStyle
import com.minhdk.wefashion.infrastructure.remote.DataApiService
import com.minhdk.wefashion.presentation.ui.common.BaseInputText
import com.minhdk.wefashion.presentation.ui.navigation.appNavBarItemConfig
import com.minhdk.wefashion.presentation.ui.navigation.base.AppBottomBar
import com.minhdk.wefashion.presentation.ui.navigation.base.singleColorNavBarItemConfig
import com.minhdk.wefashion.presentation.ui.navigation.navigationItems
import com.minhdk.wefashion.presentation.ui.theme.WeFashionTheme
import com.tomtom.sdk.init.TomTomSdk
import com.tomtom.sdk.location.GeoPoint
import com.tomtom.sdk.map.display.MapLocationInfrastructure
import com.tomtom.sdk.map.display.camera.InitialCameraOptions
import com.tomtom.sdk.map.display.compose.TomTomMap
import com.tomtom.sdk.map.display.compose.model.MapDisplayInfrastructure
import com.tomtom.sdk.map.display.compose.state.rememberMapViewState
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

                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        BaseInputText(
                            value = text,
                            onValueChange = { text = it },
                            placeholder = "Enter your email",
                            leadingIcon = Icons.Default.Preview,
                            trailingIcon = Icons.Default.LocalActivity,
                            showTrailingIcon = true,
                            trailingEnabled = true,
                            onTrailingClick = {  },

                            textColor = Color(0xFFFFFFFF),
                            placeholderColor = Color(0xFF6B6B72),
                            focusedBorderColor = Color(0xFF5B5BD6),
                            unfocusedBorderColor = Color(0xFFE5E7EB),
                            backgroundColor = Color.Transparent,
                            leadingIconColor = Color(0xFF9CA3AF),
                            trailingIconColor = Color(0xFFFFFFFF),
                            trailingIconFocusedColor = Color(0xFF5B5BD6),
                            trailingIconDisabledColor = Color(0xFF505050)
                        )
                    }

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