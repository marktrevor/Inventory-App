package com.example.inventory.ui.theme.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.inventory.R
import com.example.inventory.navigation.ROUTE_ADD_ITEM
import com.example.inventory.navigation.ROUTE_VIEW_ITEM

@Composable
fun HomeScreen(navController: NavController){
    Column(modifier = Modifier
        .fillMaxWidth()
        .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        var context= LocalContext.current // var productdata=productive wmodel(navController,context)
        
        Text(text = "WELCOME TO YOUR INVENTORY",
            color = Color.Black,
            fontFamily = FontFamily.Cursive,
            fontSize = 22.sp,
            textDecoration = TextDecoration.Underline)
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = R.drawable.dop),
            contentDescription = "TeachersIcon",
            modifier = Modifier.size(120.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            navController.navigate(ROUTE_ADD_ITEM)
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Add Item")
        }
        Spacer(modifier = Modifier.height(100.dp))
        Button(onClick = {
            navController.navigate(ROUTE_VIEW_ITEM)
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "View Item")

        }

    }

}
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun HomeScreenPreview(){
    HomeScreen(rememberNavController())
}