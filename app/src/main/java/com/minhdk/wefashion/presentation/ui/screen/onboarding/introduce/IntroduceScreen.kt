package com.minhdk.wefashion.presentation.ui.screen.onboarding.introduce

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.minhdk.wefashion.presentation.ui.common.BaseButton
import com.minhdk.wefashion.presentation.ui.common.BaseButtonBox
import com.minhdk.wefashion.presentation.ui.custom.DotIndicator
import com.minhdk.wefashion.presentation.ui.navigation.Onboarding
import com.minhdk.wefashion.presentation.ui.screen.onboarding.introduce.component.OnboardingPager
import com.minhdk.wefashion.presentation.ui.theme.Background
import com.minhdk.wefashion.presentation.ui.theme.Primary
import com.minhdk.wefashion.presentation.ui.theme.TextSecondary

@Composable
fun IntroduceScreen(
    contentPadding: PaddingValues,
    onNavigate: (Onboarding) -> Unit
) {

    val viewmodel: IntroduceViewModel = hiltViewModel()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .padding(contentPadding)
            .navigationBarsPadding()
    ) {

        val onboardingItems = viewmodel.getOnboardingItems()

        val pagerState = rememberPagerState(pageCount = { onboardingItems.size })

        OnboardingPager(
            pagerState = pagerState,
            items = onboardingItems
        )

        DotIndicator(
            quantity = onboardingItems.size,
            indicatorSize = 8.dp,
            indicatorSpacing = 5.dp,
            activeIndicatorColor = Primary,
            inactiveIndicatorColor = TextSecondary,
            selectedPos = pagerState.currentPage,
            modifier = Modifier.fillMaxWidth().height(50.dp)
        )

        BaseButtonBox(
            txt = "Create Account",
            bgColor = Primary,
            contentColor = Background,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp, vertical = 20.dp)
        ) {
            onNavigate.invoke(Onboarding.Skip)
        }

    }

}