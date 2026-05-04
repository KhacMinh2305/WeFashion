package com.minhdk.wefashion.presentation.ui.screen.onboarding.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.minhdk.wefashion.domain.repository.SharedPrefRepository
import com.minhdk.wefashion.domain.usecase.sharedpref.getFirstTravelOnboardingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val sharedRepo: SharedPrefRepository
): ViewModel() {

    private val _nextState = Channel<Boolean>()
    val nextState = _nextState.receiveAsFlow()

    init {
        viewModelScope.launch {
            delay(2000L)
            getFirstTravelOnboardingUseCase(sharedRepo) {
                _nextState.send(it)
            }
        }
    }

}