package com.minhdk.wefashion.presentation.ui.screen.onboarding.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.minhdk.wefashion.R
import com.minhdk.wefashion.presentation.ui.navigation.Onboarding
import com.minhdk.wefashion.presentation.ui.theme.Primary
import com.minhdk.wefashion.presentation.ui.theme.TextPrimaryLight

@Composable
fun SplashScreen(
    contentPadding: PaddingValues,
    onNavigate: (Onboarding) -> Unit
) {

    val viewmodel: SplashViewModel = hiltViewModel()

    LaunchedEffect(Unit) {
        viewmodel.nextState.collect {
            onNavigate(if(it) Onboarding.Introduce else Onboarding.End)
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Primary)
            .padding(contentPadding)
            .navigationBarsPadding()
    ) {

        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.displayMedium,
            color = TextPrimaryLight
        )

        Spacer(
            modifier = Modifier.fillMaxWidth().height(15.dp)
        )

        Text(
            text = stringResource(id = R.string.splash_title),
            style = MaterialTheme.typography.bodyMedium,
            color = TextPrimaryLight
        )
    }

}

