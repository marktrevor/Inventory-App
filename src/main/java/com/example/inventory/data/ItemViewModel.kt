package com.example.inventory.data

import android.app.ProgressDialog
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavController
import com.example.inventory.models.Item
import com.example.inventory.navigation.ROUTE_LOGIN
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
class ItemViewModel(var navController: NavController, var context: Context) {
    fun updateItem(name: String, quantity: String, price: String, dateofpurchase:String, id: String) {
        var updateRef = FirebaseDatabase.getInstance().getReference()
            .child("Items/$id")
        progress.show()

        var updateData = Item(name,quantity,price, dateofpurchase, id)
        updateRef.setValue(updateData).addOnCompleteListener {
            progress.dismiss()
            if (it.isSuccessful) {
                Toast.makeText(context, "Update successful", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun saveItem(
        itemName: String,
        itemQuantity: String,
        itemPrice: String,
        itemDateofPurchase: String
    ) {

        var id = System.currentTimeMillis().toString()
        var itemData = Item(
            itemName,
            itemQuantity,
            itemPrice,
            itemDateofPurchase,
            id
        )
        var itemRef = FirebaseDatabase.getInstance().getReference()
            .child("Items/$id")
        progress.show()
        itemRef.setValue(itemData).addOnCompleteListener {
            progress.dismiss()
            if (it.isSuccessful) {
                Toast.makeText(context, "Saving successful", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "ERROR: ${it.exception!!.message}", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }


    fun viewItems(
        item: MutableState<Item>,
        items: SnapshotStateList<Item>
    ): SnapshotStateList<Item> {
        val ref = FirebaseDatabase.getInstance().getReference().child("Items")

        progress.show()
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                progress.dismiss()
                items.clear()
                for (snap in snapshot.children) {
                    val value = snap.getValue(Item::class.java)
                    item.value = value!!
                    items.add(value)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })
        return items
    }

    fun deleteItem(id: String) {
        var delRef = FirebaseDatabase.getInstance().getReference()
            .child("Items/$id")
        progress.show()
        delRef.removeValue().addOnCompleteListener {
            progress.dismiss()
            if (it.isSuccessful) {
                Toast.makeText(context, "Item deleted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
            }
        }

    }


    var authRepository: AuthViewModel
    var progress: ProgressDialog

    init {
        authRepository = AuthViewModel(navController, context)
        if (!authRepository.isloggedin()) {
            navController.navigate(ROUTE_LOGIN)
        }
        progress = ProgressDialog(context)
        progress.setTitle("Loading")
        progress.setMessage("Please wait...")
    }


}