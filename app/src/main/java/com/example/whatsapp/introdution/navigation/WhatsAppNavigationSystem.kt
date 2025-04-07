package com.example.whatsapp.introdution.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.whatsapp.introdution.callscreen.CallScreen
import com.example.whatsapp.introdution.commuinitiesscreen.CommunitiesScreen
import com.example.whatsapp.introdution.homescreen.HomeScreen
import com.example.whatsapp.introdution.profile.userProfileSetScreen
import com.example.whatsapp.introdution.registerscreen.RegisterScreen
import com.example.whatsapp.introdution.splashscreen.SplashScreen
import com.example.whatsapp.introdution.updatescreen.UpdateScreen
import com.example.whatsapp.introdution.viewmodels.BaseViewModel
import com.example.whatsapp.introdution.welcomescreen.WelcomeScreen

@Composable
fun WhatsAppNavigationSystem(){
    val navController = rememberNavController()
    NavHost(startDestination = Routes.SplashScreen, navController = navController){
        composable<Routes.SplashScreen>{
            SplashScreen(navController)
        }
        composable<Routes.WelcomeScreen> {
            WelcomeScreen(navController)
        }
        composable<Routes.UserRegistrationScreen> {
            RegisterScreen(navController)
        }
        composable<Routes.HomeScreen> {
            val baseViewModel: BaseViewModel = hiltViewModel()
            HomeScreen(navController, baseViewModel)
        }
        composable<Routes.UpdateScreen> {
            UpdateScreen(navController)
        }
        composable<Routes.CommunitiesScreen> {
            CommunitiesScreen(navController)
        }
        composable<Routes.CallScreen> {
            CallScreen(navController)
        }
        composable<Routes.UserProfileSetScreen> {

            userProfileSetScreen(navHostController = navController)
        }
    }
}