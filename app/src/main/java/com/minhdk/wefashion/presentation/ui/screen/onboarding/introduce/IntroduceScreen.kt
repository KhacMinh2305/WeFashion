package com.minhdk.wefashion.presentation.ui.screen.onboarding.introduce

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.minhdk.wefashion.presentation.ui.theme.Background

@Composable
fun IntroduceScreen(
    contentPadding: PaddingValues
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .padding(contentPadding)
            .navigationBarsPadding()
    ) {

    }

}