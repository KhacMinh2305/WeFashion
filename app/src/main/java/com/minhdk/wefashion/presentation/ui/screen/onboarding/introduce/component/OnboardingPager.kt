package com.minhdk.wefashion.presentation.ui.screen.onboarding.introduce.component

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.minhdk.wefashion.domain.data.onboarding.DtoOnboarding

@Composable
fun ColumnScope.OnboardingPager(
    pagerState: PagerState,
    items: List<DtoOnboarding>
) {
    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxWidth().weight(1f)
    ) { page ->
        OnboardingItem(items[page])
    }
}