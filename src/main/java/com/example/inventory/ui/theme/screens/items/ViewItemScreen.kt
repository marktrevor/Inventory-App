package com.example.inventory.ui.theme.screens.items

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.inventory.data.ItemViewModel
import com.example.inventory.models.Item
import com.example.inventory.navigation.ROUTE_UPDATE_ITEM

@Composable
fun ViewItemsScreen(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        val context = LocalContext.current
        val itemRepository = ItemViewModel(navController, context)
        val emptyItemState = remember { mutableStateOf(Item("","","","","")) }
        val emptyItemsListState = remember { mutableStateListOf<Item>() }
         val items = itemRepository.viewItems(emptyItemState,emptyItemsListState)



        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "All items",
                fontSize = 30.sp,
                fontFamily = FontFamily.Cursive,
                color = Color.Black)

            Spacer(modifier = Modifier.height(20.dp))
 Box( ){
     LazyColumn(){
     items(items){
         ViewItem(
             name = it.name,
             quantity = it.quantity,
             price = it.price,
             dateofpurchase= it.dateofpurchase,
             id= it.id,
             navController = navController,
             itemRepository = itemRepository
         )
     }
 }

 }

        }
    }

}


@Composable
fun ViewItem(name:String, quantity:String, price:String,dateofpurchase: String, id:String,
                navController: NavHostController, itemRepository:ItemViewModel) {

    Column(modifier = Modifier.fillMaxWidth(),

        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Text(text = name,
            fontFamily = FontFamily.Cursive,
            color = Color.Black)
        Text(text = quantity,
            fontFamily = FontFamily.Cursive,
            color = Color.Black
            )
        Text(text = price,
            fontFamily = FontFamily.Cursive,
            color = Color.Black)
        Text(text= dateofpurchase,
            fontFamily = FontFamily.Cursive,
            color = Color.Black)
        Button(onClick = {
            itemRepository.deleteItem(id)
        }) {
            Text(text = "Delete")
        }
        Button(onClick = {
            navController.navigate(ROUTE_UPDATE_ITEM+"/$id")
        }) {
            Text(text = "Update")
        }
    }

}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun View() {
    ViewItemsScreen(rememberNavController())

}