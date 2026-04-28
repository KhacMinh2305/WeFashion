package com.minhdk.wefashion.presentation

import android.app.Application
import com.minhdk.wefashion.BuildConfig
import com.tomtom.sdk.common.configuration.buildSdkConfiguration
import com.tomtom.sdk.init.TomTomSdk
import com.tomtom.sdk.telemetry.UserConsent
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@HiltAndroidApp
class WeFashionApp: Application() {

    override fun onCreate() {
        super.onCreate()
        initializeMap()
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun initializeMap() {
        GlobalScope.launch {
            if(!TomTomSdk.isInitialized) {
                TomTomSdk.initialize(
                    context = applicationContext,
                    sdkConfiguration = buildSdkConfiguration(
                        context = applicationContext,
                        telemetryUserConsent = suspend { UserConsent.TelemetryOn },
                        apiKey = BuildConfig.TOMTOM_API_KEY,
                    )
                )
            }
        }
    }

}