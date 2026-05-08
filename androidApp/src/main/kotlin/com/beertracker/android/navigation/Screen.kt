package com.beertracker.android.navigation

sealed class Screen(val route: String) {
    data object Splash : Screen("splash")
    data object Login : Screen("login")
    data object Register : Screen("register")
    data object Home : Screen("home")
    data object StyleList : Screen("style_list")
    data object StyleDetail : Screen("style_detail/{styleIndex}") {
        fun createRoute(styleIndex: Int) = "style_detail/$styleIndex"
    }
    data object Driver : Screen("driver")
    data object ChosenDriver : Screen("chosen_driver/{driverName}") {
        fun createRoute(driverName: String) = "chosen_driver/$driverName"
    }
}
