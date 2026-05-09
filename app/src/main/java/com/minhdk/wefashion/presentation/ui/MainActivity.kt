package com.minhdk.wefashion.presentation.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
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
import androidx.navigation.toRoute
import com.minhdk.wefashion.infrastructure.remote.api.DataApiService
import com.minhdk.wefashion.presentation.ui.navigation.Authentication
import com.minhdk.wefashion.presentation.ui.navigation.Cart
import com.minhdk.wefashion.presentation.ui.navigation.Home
import com.minhdk.wefashion.presentation.ui.navigation.Onboarding
import com.minhdk.wefashion.presentation.ui.navigation.Order
import com.minhdk.wefashion.presentation.ui.navigation.Setting
import com.minhdk.wefashion.presentation.ui.navigation.appNavBarItemConfig
import com.minhdk.wefashion.presentation.ui.navigation.base.AppBottomBar
import com.minhdk.wefashion.presentation.ui.navigation.navigationItems
import com.minhdk.wefashion.presentation.ui.screen.authentication.forgot.ForgotPasswordScreen
import com.minhdk.wefashion.presentation.ui.screen.authentication.login.LoginScreen
import com.minhdk.wefashion.presentation.ui.screen.authentication.register.RegisterScreen
import com.minhdk.wefashion.presentation.ui.screen.authentication.verification.VerificationScreen
import com.minhdk.wefashion.presentation.ui.screen.home.main.MainScreen
import com.minhdk.wefashion.presentation.ui.screen.home.search.SearchScreen
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
                ForgotPasswordScreen(contentPadding) { navigateAuthentication(navController, it) }
            }
            composable<Authentication.Verification> { backStackEntry ->
                val route = backStackEntry.toRoute<Authentication.Verification>()
                VerificationScreen(
                    contentPadding = contentPadding,
                    email = route.email,
                    credential = route.credential
                ) { dest ->
                    navigateAuthentication(navController, dest)
                }
            }
        }
    }

    private fun NavGraphBuilder.appFlow(navController: NavHostController, contentPadding: PaddingValues) {
        appFlowHome(navController, contentPadding)
        appFlowOrder(navController)
        appFlowContact(navController)
        appFlowSetting(navController)
    }

    private fun NavGraphBuilder.appFlowHome(navController: NavHostController, contentPadding: PaddingValues) {
        navigation<Home.HomeFlow>(startDestination = Home.Main) {
            composable<Home.Main> {
                MainScreen(contentPadding) {
                    handleHomeNavigation(navController, it)
                }
            }
//            composable<Home.Profile> {}
            composable<Home.Search> {
                SearchScreen(contentPadding) {
                    handleHomeNavigation(navController, it)
                }
            }
            composable<Home.ProductDetail> {

            }
        }
    }

    private fun NavGraphBuilder.appFlowOrder(navController: NavHostController) {
        navigation<Order.OrderFlow>(startDestination = Order.MyOrder) {
            composable<Order.MyOrder> {}
            composable<Order.OrderDetail> {}
            composable<Order.OrderTracking> {}
        }
    }

    private fun NavGraphBuilder.appFlowContact(navController: NavHostController) {
        navigation<Cart.CartFlow>(startDestination = Cart.CartMain) {
            composable<Cart.CartMain> {}
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
            destination.hasRoute<Home.Search>() -> pos = 0
            destination.hasRoute<Home.ProductDetail>() -> pos = 0

            destination.hasRoute<Order.MyOrder>() -> pos = 1
            destination.hasRoute<Order.OrderDetail>() -> pos = 1
            destination.hasRoute<Order.OrderTracking>() -> pos = 1

            destination.hasRoute<Cart.CartMain>() -> pos = 2

            destination.hasRoute<Setting.General>() -> pos = 3
        }
        return pos
    }

    private fun handleUserNavigateTab(navController: NavHostController, position: Int) {
        if (position !in 0 until navigationItems.size) return
        val tabRoute = when(position) {
            0 -> Home.HomeFlow
            1 -> Order.OrderFlow
            2 -> Cart.CartFlow
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
                navController.navigate(Authentication.Verification(route.email, route.credential))
            }
            is Authentication.Reset -> {
                navController.popBackStack(Authentication.Login, false, saveState = false)
            }
            is Authentication.Back -> {
                navController.navigateUp()
            }
            is Authentication.End -> {
                navController.navigate(Home.HomeFlow) {
                    popUpTo(0) { inclusive = true }
                }
            }
            else -> {}
        }
    }

    private fun handleHomeNavigation(navController: NavHostController, route: Home) {
        when (route) {
            is Home.Main -> {
                navController.navigate(Home.Main)
            }

//            is Home.Profile -> {
//                navController.navigate(Home.Profile)
//            }

            is Home.Search -> {
                navController.navigate(Home.Search)
            }

            is Home.ProductDetail -> {
                navController.navigate(Home.ProductDetail(route.productId))
            }

            is Home.Back -> {
                navController.navigateUp()
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