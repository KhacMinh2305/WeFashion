package com.minhdk.wefashion.presentation.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.minhdk.wefashion.infrastructure.remote.api.DataApiService
import com.minhdk.wefashion.presentation.ui.navigation.Authentication
import com.minhdk.wefashion.presentation.ui.navigation.Contact
import com.minhdk.wefashion.presentation.ui.navigation.Home
import com.minhdk.wefashion.presentation.ui.navigation.Onboarding
import com.minhdk.wefashion.presentation.ui.navigation.Order
import com.minhdk.wefashion.presentation.ui.navigation.Setting
import com.minhdk.wefashion.presentation.ui.navigation.appNavBarItemConfig
import com.minhdk.wefashion.presentation.ui.navigation.base.AppBottomBar
import com.minhdk.wefashion.presentation.ui.navigation.navigationItems
import com.minhdk.wefashion.presentation.ui.screen.authentication.login.LoginScreen
import com.minhdk.wefashion.presentation.ui.screen.authentication.register.RegisterScreen
import com.minhdk.wefashion.presentation.ui.screen.onboarding.introduce.IntroduceScreen
import com.minhdk.wefashion.presentation.ui.screen.onboarding.splash.SplashScreen
import com.minhdk.wefashion.presentation.ui.theme.WeFashionTheme
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

                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()

                @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        val tabPos = getNavbarCurrentTabPosition(navBackStackEntry)
                        if(tabPos in 0 until navigationItems.size) {
                            AppBottomBar(
                                items = navigationItems,
                                itemConfig = appNavBarItemConfig(),
                                isSelected = { it == tabPos }
                            ) {
                                handleUserNavigateTab(navController, it)
                            }
                        }
                    }
                ) { paddingValues ->
                    SetupNavigation(navController, paddingValues)
                }
            }
        }
    }

    private fun NavGraphBuilder.onboardingFlow(navController: NavHostController, contentPadding: PaddingValues) {
        navigation<Onboarding.OnboardingFlow>(startDestination = Onboarding.Splash) {
            composable<Onboarding.Splash> {
                SplashScreen(contentPadding) { navigateOnboarding(navController, it) }
            }
            composable<Onboarding.Introduce> {
                IntroduceScreen(contentPadding) { navigateOnboarding(navController, it) }
            }
        }
    }

    private fun NavGraphBuilder.authenticationFlow(navController: NavHostController, contentPadding: PaddingValues) {
        navigation<Authentication.AuthFlow>(startDestination = Authentication.Login) {
            composable<Authentication.Login> {
                LoginScreen(contentPadding) { navigateAuthentication(navController, it) }
            }
            composable<Authentication.Register> {
                RegisterScreen(contentPadding) { navigateAuthentication(navController, it) }
            }
            composable<Authentication.ForgotPassword> {
                TestScreen("ForgotPassword")
            }
            composable<Authentication.Verification> {
                TestScreen("Verification")
            }
        }
    }

    private fun NavGraphBuilder.appFlow(navController: NavHostController, contentPadding: PaddingValues) {
        appFlowHome(navController)
        appFlowOrder(navController)
        appFlowContact(navController)
        appFlowSetting(navController)
    }

    private fun NavGraphBuilder.appFlowHome(navController: NavHostController) {
        navigation<Home.HomeFlow>(startDestination = Home.Main) {
            composable<Home.Main> {
                TestScreen("Main")
            }
            composable<Home.Profile> {}
            composable<Home.Search> {}
            composable<Home.Notification> {}
            composable<Home.ListProducts> {}
        }
    }

    private fun NavGraphBuilder.appFlowOrder(navController: NavHostController) {
        navigation<Order.OrderFlow>(startDestination = Order.MyOrder) {
            composable<Order.MyOrder> {}
            composable<Order.OrderDetail> {}
            composable<Order.OrderTracking> {}
            composable<Order.Cart> {}
        }
    }

    private fun NavGraphBuilder.appFlowContact(navController: NavHostController) {
        navigation<Contact.ContactFlow>(startDestination = Contact.Message) {
            composable<Contact.Message> {}
            composable<Contact.Assistant> {}
        }
    }

    private fun NavGraphBuilder.appFlowSetting(navController: NavHostController) {
        navigation<Setting.SettingFlow>(startDestination = Setting.General) {
            composable<Setting.General> {}
        }
    }

    @Composable
    private fun SetupNavigation(navController: NavHostController, contentPadding: PaddingValues) {
        NavHost(navController = navController, startDestination = Onboarding.OnboardingFlow) {
            onboardingFlow(navController, contentPadding)
            authenticationFlow(navController, contentPadding)
            appFlow(navController, contentPadding)
        }
    }

    private fun getNavbarCurrentTabPosition(entry: NavBackStackEntry?): Int {
        val destination = (entry ?: return - 1 ).destination
        var pos = -1
        when {
            destination.hasRoute<Home.Main>() -> pos = 0
            destination.hasRoute<Home.Profile>() -> pos = 0
            destination.hasRoute<Home.Search>() -> pos = 0
            destination.hasRoute<Home.Notification>() -> pos = 0
            destination.hasRoute<Home.ListProducts>() -> pos = 0

            destination.hasRoute<Order.MyOrder>() -> pos = 1
            destination.hasRoute<Order.OrderDetail>() -> pos = 1
            destination.hasRoute<Order.OrderTracking>() -> pos = 1
            destination.hasRoute<Order.Cart>() -> pos = 1

            destination.hasRoute<Contact.Message>() -> pos = 2
            destination.hasRoute<Contact.Assistant>() -> pos = 2

            destination.hasRoute<Setting.General>() -> pos = 3
        }
        return pos
    }

    private fun handleUserNavigateTab(navController: NavHostController, position: Int) {
        if (position !in 0 until navigationItems.size) return
        val tabRoute = when(position) {
            0 -> Home.HomeFlow
            1 -> Order.OrderFlow
            2 -> Contact.ContactFlow
            3 -> Setting.SettingFlow
            else -> return
        }

        navController.navigate(tabRoute) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    @Composable
    private fun TestScreen(title: String) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize().padding(12.dp).background(Color.Black)
        ) {
            Text(
                color = Color.White,
                text = title,
                fontSize = 20.sp
            )
        }
    }

    private fun navigateOnboarding(navController: NavHostController, route: Onboarding) {
        when(route) {
            Onboarding.Introduce -> {
                navController.navigate(Onboarding.Introduce)
            }
            Onboarding.End -> {
                navController.navigate(Authentication.AuthFlow) {
                    popUpTo(0) { inclusive = true }
                }
            }
            else -> {}
        }
    }

    private fun navigateAuthentication(navController: NavHostController, route: Authentication) {
        when(route) {
            is Authentication.Login -> {
                navController.navigate(Authentication.Login)
            }
            is Authentication.Register -> {
                navController.navigate(Authentication.Register)
            }
            is Authentication.ForgotPassword -> {
                navController.navigate(Authentication.ForgotPassword)
            }
            is Authentication.Verification -> {

            }
            is Authentication.Back -> {
                navController.popBackStack(Authentication.Login, false, saveState = false)
            }
            is Authentication.End -> {
                navController.navigate(Home.HomeFlow) {
                    popUpTo(0) { inclusive = true }
                }
            }
            else -> {}
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