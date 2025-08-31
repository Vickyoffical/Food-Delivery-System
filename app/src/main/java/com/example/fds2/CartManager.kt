package com.example.fds2

object CartManager {

    // Stores item name -> (quantity, price per unit)
    val cartItems = mutableMapOf<String, Pair<Int, Int>>()

    // Add one unit of the item
    fun addItems(itemName: String, price: Int) {
        val current = cartItems[itemName]
        if (current != null) {
            val newQuantity = current.first + 1
            cartItems[itemName] = Pair(newQuantity, price)
        } else {
            cartItems[itemName] = Pair(1, price)
        }
    }

    // Remove one unit of the item
    fun removeItems(itemName: String) {
        val current = cartItems[itemName]
        if (current != null) {
            val newQuantity = current.first - 1
            if (newQuantity > 0) {
                cartItems[itemName] = Pair(newQuantity, current.second)
            } else {
                cartItems.remove(itemName)
            }
        }
    }

    // Get total price of all items
    fun getTotal(): Int {
        return cartItems.values.sumOf { (quantity, pricePerUnit) ->
            quantity * pricePerUnit
        }
    }

    // Get total number of items in cart (quantity sum)
    fun getItemCount(): Int {
        return cartItems.values.sumOf { it.first }
    }

    // Get quantity of a specific item
    fun getItemQuantity(itemName: String): Int {
        return cartItems[itemName]?.first ?: 0
    }

    // Get price per unit of an item
    fun getItemPrice(itemName: String): Int {
        return cartItems[itemName]?.second ?: 0
    }

    // Clear the entire cart (optional)
    fun clearCart() {
        cartItems.clear()
    }
}
