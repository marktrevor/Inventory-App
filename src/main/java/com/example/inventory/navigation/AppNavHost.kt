package com.example.inventory.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavDestination

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.inventory.ui.theme.screens.home.HomeScreen
import com.example.inventory.ui.theme.screens.items.AddItemsScreen
import com.example.inventory.ui.theme.screens.items.UpdateItemsScreen
import com.example.inventory.ui.theme.screens.items.ViewItem
import com.example.inventory.ui.theme.screens.items.ViewItemsScreen
import com.example.inventory.ui.theme.screens.login.LoginScreen
import com.example.inventory.ui.theme.screens.register.RegisterScreen

@Composable
fun AppNavHost(modifier: Modifier= Modifier,
               navController:NavHostController = rememberNavController(),
    startDestination: String = ROUTE_REGISTER
){
    NavHost(navController= navController,
        modifier = modifier,

        startDestination =  startDestination){
        composable(ROUTE_HOME){
            HomeScreen(navController )
        }
composable(ROUTE_ADD_ITEM){
    AddItemsScreen(navController )
}

        composable(ROUTE_LOGIN){
            LoginScreen(navController)
        }
        composable(ROUTE_REGISTER){
            RegisterScreen(navController)
        }
        composable(ROUTE_VIEW_ITEM){
            ViewItemsScreen(navController)
        }
        composable(ROUTE_UPDATE_ITEM+"/{id}"){ passedData->
            UpdateItemsScreen(navController,passedData.arguments?.getString("id")!!)
        }
    }



    }



