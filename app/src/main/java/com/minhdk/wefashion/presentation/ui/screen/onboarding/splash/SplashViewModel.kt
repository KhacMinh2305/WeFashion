package com.minhdk.wefashion.presentation.ui.screen.onboarding.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.minhdk.wefashion.infrastructure.database.shared.AppSharedPref
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val sharedPref: AppSharedPref
): ViewModel() {

    private val _nextState = Channel<Boolean>()
    val nextState = _nextState.receiveAsFlow()

    init {
        viewModelScope.launch {
            delay(2000L)
            _nextState.send(sharedPref.firstOpenApp)
        }
    }

}