package com.example.inventory.ui.theme.screens.items

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.inventory.data.ItemViewModel
import com.example.inventory.navigation.ROUTE_HOME
import com.example.inventory.navigation.ROUTE_VIEW_ITEM

@Composable
fun AddItemsScreen(navController: NavController){
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        var context = LocalContext.current
        Text(
            text = "Add Item",
            fontSize = 30.sp,
            fontFamily = FontFamily.Cursive,
            color = Color.Red,
            modifier = Modifier.padding(20.dp),
            fontWeight = FontWeight.Bold,
            textDecoration = TextDecoration.Underline
        )
        var itemName by remember { mutableStateOf(TextFieldValue("")) }
        var itemQuantity by remember { mutableStateOf(TextFieldValue("")) }
        var itemPrice by remember { mutableStateOf(TextFieldValue("")) }
        var itemDateofPurchase by remember { mutableStateOf(TextFieldValue("")) }


        OutlinedTextField(
            value = itemName,
            onValueChange = { itemName = it },
            label = { Text(text = "Item name *") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = itemQuantity,
            onValueChange = { itemQuantity = it },
            label = { Text(text = "Item quantity *") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = itemPrice,
            onValueChange = { itemPrice = it },
            label = { Text(text = " Item price *") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = itemDateofPurchase,
            onValueChange = { itemDateofPurchase= it },
            label = { Text(text = " Date purchased *") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {

            val itemRepository = ItemViewModel(navController,context)
            itemRepository.saveItem(itemName.text.trim(),itemQuantity.text.trim(),
                itemPrice.text.trim(), itemDateofPurchase.text)
            navController.navigate(ROUTE_HOME)


        }) {
            Text(text = "Save")
        }
        Spacer(modifier = Modifier.height(20.dp))

}
}
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun AddItemsScreenPreview(){
    AddItemsScreen(rememberNavController())
}