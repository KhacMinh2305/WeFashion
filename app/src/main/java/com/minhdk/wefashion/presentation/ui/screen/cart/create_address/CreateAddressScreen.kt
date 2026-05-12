package com.minhdk.wefashion.presentation.ui.screen.cart.create_address

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.minhdk.wefashion.R
import com.minhdk.wefashion.domain.data.address.DtoAddress
import com.minhdk.wefashion.presentation.ui.common.BaseButtonBox
import com.minhdk.wefashion.presentation.ui.common.BaseInputText
import com.minhdk.wefashion.presentation.ui.theme.Background
import com.minhdk.wefashion.presentation.ui.theme.DisableButton
import com.minhdk.wefashion.presentation.ui.theme.Primary
import com.minhdk.wefashion.presentation.ui.theme.TextPrimaryLight
import com.minhdk.wefashion.presentation.ui.theme.TextSecondary
import com.minhdk.wefashion.presentation.ui.theme.roundedTop
import com.minhdk.wefashion.util.helper.logD
import com.minhdk.wefashion.util.permission.rememberPermissionLauncher
import com.tomtom.sdk.init.TomTomSdk
import com.tomtom.sdk.location.GeoPoint
import com.tomtom.sdk.map.display.MapLocationInfrastructure
import com.tomtom.sdk.map.display.camera.InitialCameraOptions
import com.tomtom.sdk.map.display.compose.TomTomMap
import com.tomtom.sdk.map.display.compose.model.MapDisplayInfrastructure
import com.tomtom.sdk.map.display.compose.state.rememberMapViewState
import kotlinx.coroutines.launch

private val TURTLE_TOWER_POINT = GeoPoint(latitude = 21.027778, longitude = 105.852222)
private const val INITIAL_CAMERA_ZOOM = 12.0

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun CreateAddressScreen(
    contentPadding: PaddingValues,
    onCreated: (DtoAddress) -> Unit
) {
    val viewModel: CreateAddressViewModel = hiltViewModel()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    val permissionLauncher = rememberPermissionLauncher(permissions = listOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    ))

    val scope = rememberCoroutineScope()

    val context = LocalContext.current

    var hasPermission by remember { mutableStateOf(context.hasMapPermissions()) }

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is CreateAddressEffect.ShowToast -> {
                    Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                }
                is CreateAddressEffect.Created -> {
                    onCreated(effect.address)
                }
            }
        }
    }

    val content: @Composable () -> Unit = {
        Box(
            modifier = Modifier.fillMaxSize().padding(contentPadding)
        ) {

            MapBox(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .align(Alignment.TopCenter)) { lat, long ->
                viewModel.onIntent(CreateAddressIntent.PositionChanged(lat, long))
            }

            CreateAddressForm(modifier = Modifier
                .fillMaxWidth().
                fillMaxHeight(0.6f)
                .background(color = Background, shape = roundedTop(16))
                .align(Alignment.BottomCenter),
                name = uiState.name,
                onNameChange = { viewModel.onIntent(CreateAddressIntent.NameChanged(it)) },
                ward = uiState.ward,
                onWardChange = { viewModel.onIntent(CreateAddressIntent.WardChanged(it)) },
                district = uiState.district,
                onDistrictChange = { viewModel.onIntent(CreateAddressIntent.DistrictChanged(it)) },
                city = uiState.city,
                onCityChange = { viewModel.onIntent(CreateAddressIntent.CityChanged(it)) },
                detail = uiState.detail,
                onDetailChange = { viewModel.onIntent(CreateAddressIntent.DetailChanged(it)) },
                receiverName = uiState.receiverName,
                onReceiverNameChange = { viewModel.onIntent(CreateAddressIntent.ReceiverNameChanged(it)) },
                phone = uiState.phone,
                onPhoneChange = { viewModel.onIntent(CreateAddressIntent.PhoneChanged(it)) },
                provideLat = { uiState.latitude },
                provideLong = { uiState.longitude },
                isSubmitting = uiState.isSubmitting,
                onCreate = { viewModel.onIntent(CreateAddressIntent.Submit) }
            )

        }
    }


    if(hasPermission) {
        content()
    } else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Location permissions are required to create an address",
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextSecondary,
                    modifier = Modifier.padding(horizontal = 32.dp),
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
                BaseButtonBox(
                    txt = "Grant Permissions",
                    bgColor = Primary,
                    contentColor = TextPrimaryLight,
                    modifier = Modifier.width(250.dp)
                ) {
                    scope.launch {
                        val granted = permissionLauncher.request()
                        hasPermission = context.hasMapPermissions()
                        if (!granted) context.goToSettings()
                    }
                }
            }
        }
    }

}

@Composable
private fun CreateAddressForm(
    modifier: Modifier = Modifier,
    name: String,
    onNameChange: (String) -> Unit,
    ward: String,
    onWardChange: (String) -> Unit,
    district: String,
    onDistrictChange: (String) -> Unit,
    city: String,
    onCityChange: (String) -> Unit,
    detail: String,
    onDetailChange: (String) -> Unit,
    receiverName: String,
    onReceiverNameChange: (String) -> Unit,
    phone: String,
    onPhoneChange: (String) -> Unit,
    provideLat: () -> Double,
    provideLong: () -> Double,
    isSubmitting: Boolean,
    onCreate: () -> Unit
) {
    val labelStyle = MaterialTheme.typography.bodyMedium
    val textColor = MaterialTheme.colorScheme.onSurface
    val placeholderColor = MaterialTheme.colorScheme.onSurfaceVariant
    val focusedBorderColor = MaterialTheme.colorScheme.primary
    val unfocusedBorderColor = MaterialTheme.colorScheme.outline
    val iconColor = MaterialTheme.colorScheme.onSurfaceVariant

    val isPhoneValid = phone.all { it.isDigit() }
    val areFieldsFilled = listOf(
        name,
        ward,
        district,
        city,
        detail,
        receiverName,
        phone
    ).all { it.isNotBlank() }
    val areCoordinatesValid = provideLat() >= 0.0 && provideLong() >= 0.0 && !provideLat().isNaN() && !provideLong().isNaN()
    val isFormValid = areFieldsFilled && isPhoneValid && areCoordinatesValid

    LazyColumn(
        modifier = modifier.padding(horizontal = 16.dp, vertical = 12.dp),
        contentPadding = PaddingValues(bottom = 24.dp)
    ) {
        item {
            Text(text = "Address name", style = labelStyle, color = textColor)
            Spacer(modifier = Modifier.height(8.dp))
            BaseInputText(
                value = name,
                onValueChange = onNameChange,
                placeholder = "Home, Office...",
                textColor = textColor,
                placeholderColor = placeholderColor,
                focusedBorderColor = focusedBorderColor,
                unfocusedBorderColor = unfocusedBorderColor,
                backgroundColor = Color.Transparent,
                leadingIconColor = iconColor,
                trailingIconColor = iconColor,
                trailingIconFocusedColor = focusedBorderColor,
                trailingIconDisabledColor = iconColor
            )
            Spacer(modifier = Modifier.height(12.dp))
        }

        item {
            Text(text = "Ward", style = labelStyle, color = textColor)
            Spacer(modifier = Modifier.height(8.dp))
            BaseInputText(
                value = ward,
                onValueChange = onWardChange,
                placeholder = "Ward",
                textColor = textColor,
                placeholderColor = placeholderColor,
                focusedBorderColor = focusedBorderColor,
                unfocusedBorderColor = unfocusedBorderColor,
                backgroundColor = Color.Transparent,
                leadingIconColor = iconColor,
                trailingIconColor = iconColor,
                trailingIconFocusedColor = focusedBorderColor,
                trailingIconDisabledColor = iconColor
            )
            Spacer(modifier = Modifier.height(12.dp))
        }

        item {
            Text(text = "District", style = labelStyle, color = textColor)
            Spacer(modifier = Modifier.height(8.dp))
            BaseInputText(
                value = district,
                onValueChange = onDistrictChange,
                placeholder = "District",
                textColor = textColor,
                placeholderColor = placeholderColor,
                focusedBorderColor = focusedBorderColor,
                unfocusedBorderColor = unfocusedBorderColor,
                backgroundColor = Color.Transparent,
                leadingIconColor = iconColor,
                trailingIconColor = iconColor,
                trailingIconFocusedColor = focusedBorderColor,
                trailingIconDisabledColor = iconColor
            )
            Spacer(modifier = Modifier.height(12.dp))
        }

        item {
            Text(text = "City", style = labelStyle, color = textColor)
            Spacer(modifier = Modifier.height(8.dp))
            BaseInputText(
                value = city,
                onValueChange = onCityChange,
                placeholder = "City",
                textColor = textColor,
                placeholderColor = placeholderColor,
                focusedBorderColor = focusedBorderColor,
                unfocusedBorderColor = unfocusedBorderColor,
                backgroundColor = Color.Transparent,
                leadingIconColor = iconColor,
                trailingIconColor = iconColor,
                trailingIconFocusedColor = focusedBorderColor,
                trailingIconDisabledColor = iconColor
            )
            Spacer(modifier = Modifier.height(12.dp))
        }

        item {
            Text(text = "Address detail", style = labelStyle, color = textColor)
            Spacer(modifier = Modifier.height(8.dp))
            BaseInputText(
                value = detail,
                onValueChange = onDetailChange,
                placeholder = "Street, house number...",
                textColor = textColor,
                placeholderColor = placeholderColor,
                focusedBorderColor = focusedBorderColor,
                unfocusedBorderColor = unfocusedBorderColor,
                backgroundColor = Color.Transparent,
                leadingIconColor = iconColor,
                trailingIconColor = iconColor,
                trailingIconFocusedColor = focusedBorderColor,
                trailingIconDisabledColor = iconColor
            )
            Spacer(modifier = Modifier.height(12.dp))
        }

        item {
            Text(text = "Receiver name", style = labelStyle, color = textColor)
            Spacer(modifier = Modifier.height(8.dp))
            BaseInputText(
                value = receiverName,
                onValueChange = onReceiverNameChange,
                placeholder = "Full name",
                textColor = textColor,
                placeholderColor = placeholderColor,
                focusedBorderColor = focusedBorderColor,
                unfocusedBorderColor = unfocusedBorderColor,
                backgroundColor = Color.Transparent,
                leadingIconColor = iconColor,
                trailingIconColor = iconColor,
                trailingIconFocusedColor = focusedBorderColor,
                trailingIconDisabledColor = iconColor
            )
            Spacer(modifier = Modifier.height(12.dp))
        }

        item {
            Text(text = "Phone", style = labelStyle, color = textColor)
            Spacer(modifier = Modifier.height(8.dp))
            BaseInputText(
                value = phone,
                onValueChange = onPhoneChange,
                placeholder = "Phone number",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                textColor = textColor,
                placeholderColor = placeholderColor,
                focusedBorderColor = focusedBorderColor,
                unfocusedBorderColor = unfocusedBorderColor,
                backgroundColor = Color.Transparent,
                leadingIconColor = iconColor,
                trailingIconColor = iconColor,
                trailingIconFocusedColor = focusedBorderColor,
                trailingIconDisabledColor = iconColor
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {

            BaseButtonBox (
                txt = "Create",
                enabled = !isSubmitting,
                bgColor = Primary,
                disabledBgColor = DisableButton,
                contentColor = TextPrimaryLight,
                disabledContentColor = TextSecondary
            ) {
                if (!isFormValid) return@BaseButtonBox
                onCreate()
            }
        }
    }
}

@Composable
private fun MapBox(
    modifier: Modifier = Modifier,
    onPositionChanged: (Double, Double) -> Unit = { _, _ -> }
) {

    val mapViewState = rememberMapViewState(initialCameraOptions = InitialCameraOptions.LocationBased(
        position = TURTLE_TOWER_POINT,
        zoom = INITIAL_CAMERA_ZOOM,
    ))

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        TomTomMap(
            state = mapViewState,
            infrastructure = MapDisplayInfrastructure(
                sdkContext = TomTomSdk.sdkContext,
            ) {
                locationInfrastructure = MapLocationInfrastructure {
                    locationProvider = TomTomSdk.locationProvider
                }
            },
            modifier = Modifier.fillMaxSize(),

            onMapPanningListener = {
                val center = mapViewState.cameraState.data?.position?.position
                onPositionChanged(
                    center?.latitude ?: 0.0,
                    center?.longitude ?: 0.0
                )
            },
        )

        Image(
            painter = painterResource(R.drawable.ic_map_pin),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(30.dp)
        )
    }
}

fun Context.hasMapPermissions(): Boolean {
    val locationPermission = ContextCompat.checkSelfPermission(
        this,
        Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED

    return locationPermission
}

fun Context.goToSettings() {
    val intent = android.content.Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
    intent.data = android.net.Uri.fromParts("package", packageName, null)
    startActivity(intent)
}
