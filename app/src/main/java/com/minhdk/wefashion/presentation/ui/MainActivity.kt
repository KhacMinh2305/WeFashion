package com.minhdk.wefashion.presentation.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.minhdk.wefashion.domain.data.address.DtoAddress
import com.minhdk.wefashion.presentation.ui.navigation.AppStarter
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
import com.minhdk.wefashion.presentation.ui.screen.cart.coupon.CouponScreen
import com.minhdk.wefashion.presentation.ui.screen.cart.main.CartScreen
import com.minhdk.wefashion.presentation.ui.screen.cart.payment.PaymentScreen
import com.minhdk.wefashion.presentation.ui.screen.cart.address.SelectAddressScreen
import com.minhdk.wefashion.presentation.ui.screen.cart.create_address.CreateAddressScreen
import com.minhdk.wefashion.presentation.ui.screen.cart.payment_result.PaymentResultScreen
import com.minhdk.wefashion.presentation.ui.screen.cart.process_payment.ProcessPaymentScreen
import com.minhdk.wefashion.presentation.ui.screen.home.main.MainScreen
import com.minhdk.wefashion.presentation.ui.screen.home.product.detail.ProductDetailScreen
import com.minhdk.wefashion.presentation.ui.screen.home.search.SearchScreen
import com.minhdk.wefashion.presentation.ui.screen.onboarding.introduce.IntroduceScreen
import com.minhdk.wefashion.presentation.ui.screen.onboarding.splash.SplashScreen
import com.minhdk.wefashion.presentation.ui.screen.order.list_order.ListOrderScreen
import com.minhdk.wefashion.presentation.ui.theme.WeFashionTheme
import com.minhdk.wefashion.util.helper.logD
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var onReceivedPaymentResult: ((Boolean) -> Unit)? = null

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeFashionTheme {

                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()

                LaunchedEffect(Unit) {
                    navController.navigate(Onboarding.OnboardingFlow)
                }

                LaunchedEffect(Unit) {
                    onReceivedPaymentResult = { success ->
                        navController.navigate(Cart.PaymentResult(success))
                    }
                }

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
        appFlowOrder(navController, contentPadding)
        appFlowCart(navController, contentPadding)
        appFlowSetting(navController)
    }

    private fun NavGraphBuilder.appFlowHome(navController: NavHostController, contentPadding: PaddingValues) {
        navigation<Home.HomeFlow>(startDestination = Home.Main) {
            composable<Home.Main> {
                MainScreen(contentPadding) {
                    handleHomeNavigation(navController, it)
                }
            }
            composable<Home.Search> {
                SearchScreen(contentPadding) {
                    handleHomeNavigation(navController, it)
                }
            }
            composable<Home.ProductDetail> {
                ProductDetailScreen(
                    contentPadding = contentPadding
                ) {
                    handleHomeNavigation(navController, it)
                }
            }
        }
    }

    private fun NavGraphBuilder.appFlowOrder(navController: NavHostController, contentPadding: PaddingValues) {
        navigation<Order.OrderFlow>(startDestination = Order.MyOrder) {
            composable<Order.MyOrder> {
                ListOrderScreen(contentPadding) {}
            }
            composable<Order.OrderDetail> {}
            composable<Order.OrderTracking> {}
        }
    }

    private fun NavGraphBuilder.appFlowCart(navController: NavHostController, contentPadding: PaddingValues) {
        navigation<Cart.CartFlow>(startDestination = Cart.CartMain) {
            composable<Cart.CartMain> { backStackEntry ->
                val selectedCouponName by backStackEntry.savedStateHandle
                    .getStateFlow("selectedCouponName", "")
                    .collectAsState()
                CartScreen(
                    contentPadding = contentPadding,
                    selectedCouponName = selectedCouponName,
                    onConsumeSelectedCoupon = {
                        backStackEntry.savedStateHandle["selectedCouponName"] = ""
                    },
                    onOpenCoupon = { orderTotal ->
                        handleCartNavigation(navController, Cart.Coupon(orderTotal))
                    },
                    onCheckout = { discount, shippingFee, total ->
                        handleCartNavigation(navController, Cart.Payment(discount, shippingFee, total))
                    }
                )
            }

            composable<Cart.Coupon> { backStackEntry ->
                val route = backStackEntry.toRoute<Cart.Coupon>()
                CouponScreen(
                    contentPadding = contentPadding,
                    orderTotal = route.orderTotal,
                    onSelectCoupon = { coupon ->
                        val couponName = coupon.name ?: return@CouponScreen
                        navController.previousBackStackEntry
                            ?.savedStateHandle
                            ?.set("selectedCouponName", couponName)
                        navController.navigateUp()
                    },
                    onBack = { handleCartNavigation(navController, Cart.Back) }
                )
            }

            composable<Cart.Payment> { backStackEntry ->
                val route = backStackEntry.toRoute<Cart.Payment>()
                val selectedAddress by backStackEntry.savedStateHandle
                    .getStateFlow("selectedAddress", null as DtoAddress?)
                    .collectAsState()
                PaymentScreen(
                    contentPadding = contentPadding,
                    selectedAddress = selectedAddress,
                    onBack = { handleCartNavigation(navController, Cart.Back) },
                    onEditAddress = { handleCartNavigation(navController, Cart.Address) },
                    onCheckOut = {
                        logD("midas", "handle payment link !!!!")
                        handleCartNavigation(navController, Cart.ProcessCheckout(it))
                    }
                )
            }

            composable<Cart.Address> { backStackEntry ->
                val createdAddressId by backStackEntry.savedStateHandle
                    .getStateFlow("createdAddressId", null as Int?)
                    .collectAsState()
                SelectAddressScreen(
                    contentPadding = contentPadding,
                    onBack = { handleCartNavigation(navController, Cart.Back) },
                    createAddress = {
                        handleCartNavigation(navController, Cart.CreateAddress)
                    },
                    onConfirm = { selected ->
                        navController.previousBackStackEntry
                            ?.savedStateHandle
                            ?.set("selectedAddress", selected)
                        handleCartNavigation(navController, Cart.Back)
                    },
                    createdAddressId = createdAddressId,
                    onConsumeCreatedAddress = {
                        backStackEntry.savedStateHandle["createdAddressId"] = null
                    }
                )
            }

            composable<Cart.CreateAddress> {
                CreateAddressScreen(
                    contentPadding = contentPadding,
                    onCreated = { address ->
                        navController.previousBackStackEntry
                            ?.savedStateHandle
                            ?.set("createdAddressId", address.id)
                        navController.navigateUp()
                    }
                ) {
                    handleCartNavigation(navController, it)
                }
            }

            composable<Cart.ProcessCheckout> {
                val route = it.toRoute<Cart.ProcessCheckout>()
                ProcessPaymentScreen(contentPadding = contentPadding, route.paymentLink)
            }

            composable<Cart.PaymentResult> {
                val route = it.toRoute<Cart.PaymentResult>()
                PaymentResultScreen(contentPadding, route.success) {
                    navController.popBackStack(Cart.CartMain, false, saveState = false)
                    handleUserNavigateTab(navController, 1)
                }
            }
        }
    }

    private fun NavGraphBuilder.appFlowSetting(navController: NavHostController) {
        navigation<Setting.SettingFlow>(startDestination = Setting.General) {
            composable<Setting.General> {}
        }
    }

    @Composable
    private fun SetupNavigation(navController: NavHostController, contentPadding: PaddingValues) {
        NavHost(navController = navController, startDestination = AppStarter) {
            composable<AppStarter> {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White))
            }
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
            destination.hasRoute<Cart.Coupon>() -> pos = 2
            destination.hasRoute<Cart.Payment>() -> pos = 2
            destination.hasRoute<Cart.Address>() -> pos = 2
            destination.hasRoute<Cart.CreateAddress>() -> pos = 2

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
            popUpTo(AppStarter) {
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
                    popUpTo(Onboarding.Splash) { inclusive = true }
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
                    popUpTo(Authentication.Login) { inclusive = true }
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

    private fun handleCartNavigation(navController: NavHostController, route: Cart) {
        when (route) {
            is Cart.CartMain -> {
                navController.navigate(Cart.CartMain)
            }

            is Cart.Coupon -> {
                navController.navigate(Cart.Coupon(route.orderTotal))
            }

            is Cart.Payment -> {
                navController.navigate(Cart.Payment(route.discount, route.shippingFee, route.orderTotal))
            }

            is Cart.Address -> {
                navController.navigate(Cart.Address)
            }

            is Cart.CreateAddress -> {
                navController.navigate(Cart.CreateAddress)
            }

            is Cart.ProcessCheckout -> {
                navController.navigate(Cart.ProcessCheckout(route.paymentLink))
            }

            is Cart.Back -> {
                navController.navigateUp()
            }

            else -> {}
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        val uri = intent.data ?: return
        val status = uri.getQueryParameter("status")
        val orderCode = uri.getQueryParameter("orderCode")
        Log.d("AppLink", "status=$status, orderCode=$orderCode")
        val success = if(status == "PAID") true else if(status == "CANCELLED") false else return
        onReceivedPaymentResult?.invoke(success)
    }

}

// Force app links
//  & "$env:LOCALAPPDATA\Android\Sdk\platform-tools\adb.exe" shell pm set-app-links --package com.minhdk.wefashion 1 all

// Verify
// & "$env:LOCALAPPDATA\Android\Sdk\platform-tools\adb.exe" shell pm get-app-links com.minhdk.wefashion

// status=CANCELLED, orderCode=230141
// status=PAID, orderCode=230142