package com.example.inventory.models

import androidx.navigation.NavHostController
import com.example.inventory.data.ItemViewModel

class Item {
    var name: String = ""
    var quantity: String = ""
    var price: String = ""
    var dateofpurchase: String= ""
    var id: String = ""

    constructor(name: String, quantity: String, price: String,dateofpurchase: String, id: String) {
        this.name = name
        this.quantity = quantity
        this.price = price
        this.dateofpurchase = dateofpurchase
        this.id = id
    }
    constructor(   )
}
