package com.minhdk.wefashion.presentation.ui.screen.onboarding.introduce

import androidx.lifecycle.ViewModel
import com.minhdk.wefashion.domain.data.onboarding.DtoOnboarding
import com.minhdk.wefashion.domain.repository.OnboardingRepository
import com.minhdk.wefashion.domain.repository.SharedPrefRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class IntroduceViewModel @Inject constructor(
    private val sharedRepo: SharedPrefRepository
): ViewModel() {

    fun getOnboardingItems(): List<DtoOnboarding> = OnboardingRepository.getOnboardingItems()

}