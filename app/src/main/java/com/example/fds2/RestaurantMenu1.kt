package com.example.fds2

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class RestaurantMenu1 : Fragment() {
    override fun onCreateView(i: LayoutInflater, c: ViewGroup?, s: Bundle?) =
        i.inflate(R.layout.activity_restaurant_menu1, c, false)


        private lateinit var search: EditText
        private lateinit var tvPizza: TextView
        private lateinit var tvBread: TextView
        private lateinit var tvPizzaCombo: TextView
        private lateinit var tvBreadCombo: TextView
        private lateinit var tvDrinks: TextView
        private lateinit var pizza: CardView
        private lateinit var bread: CardView
        private lateinit var pizzaCombo: CardView
        private lateinit var breadCombo: CardView
        private lateinit var drinks: CardView
        private lateinit var btnPizza: Button
        private lateinit var btnBread: Button
        private lateinit var btnPizzaCombo: Button
        private lateinit var btnBreadCombo: Button
        private lateinit var btnDrinks: Button
        private lateinit var btnOrder: Button
        private var cartCount = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        search = view.findViewById(R.id.search)
        tvPizza = view.findViewById(R.id.pizza)
        pizza = view.findViewById(R.id.pizzaCard)
        tvBread = view.findViewById(R.id.bread)
        bread = view.findViewById(R.id.breadCard)
        tvPizzaCombo = view.findViewById(R.id.pizzaCombo)
        pizzaCombo = view.findViewById(R.id.pizzaComboCard)
        tvBreadCombo = view.findViewById(R.id.breadCombo)
        breadCombo = view.findViewById(R.id.breadComboCard)
        tvDrinks = view.findViewById(R.id.drinks)
        drinks = view.findViewById(R.id.drinksCard)
        btnPizza = view.findViewById(R.id.addPizza)
        btnBread = view.findViewById(R.id.addBread)
        btnPizzaCombo = view.findViewById(R.id.addPizzaCombo)
        btnBreadCombo = view.findViewById(R.id.addBreadCombo)
        btnDrinks = view.findViewById(R.id.addDrinks)
        btnOrder = view.findViewById(R.id.btnOrder)

        fun refreshUI(){
            btnPizza.text = if (CartManager.cartItems.containsKey("Pizza")) "Remove" else "Add"
            btnBread.text = if (CartManager.cartItems.containsKey("Garlic Breadstick")) "Remove" else "Add"
            btnPizzaCombo.text = if (CartManager.cartItems.containsKey("Pizza Combo")) "Remove" else "Add"
            btnBreadCombo.text = if (CartManager.cartItems.containsKey("Bread Combo")) "Remove" else "Add"
            btnDrinks.text = if (CartManager.cartItems.containsKey("Beverages")) "Remove" else "Add"



            btnOrder.visibility = if(CartManager.cartItems.isNotEmpty()) View.VISIBLE else View.GONE
    }

        fun toggleButtonText(button: Button, itemName: String, price: Int) {
            if (button.text.toString() == "Add") {
                button.text = "Remove"
                CartManager.addItems(itemName, price)
            } else {
                button.text = "Add"
                CartManager.removeItems(itemName)
            }
            refreshUI()
        }

        btnOrder.visibility = if(CartManager.cartItems.isNotEmpty()) View.VISIBLE else View.GONE

        refreshUI()

        btnPizza.setOnClickListener {
            toggleButtonText(btnPizza, "Pizza", 120)
            refreshUI()
        }

        btnBread.setOnClickListener {
            toggleButtonText(btnBread, "Garlic Breadstick", 90)
            refreshUI()
        }

        btnPizzaCombo.setOnClickListener {
            toggleButtonText(btnPizzaCombo, "Pizza Combo",240)
            refreshUI()
        }

        btnBreadCombo.setOnClickListener {
            toggleButtonText(btnBreadCombo, "Bread Combo",180)
            refreshUI()
        }

        btnDrinks.setOnClickListener {
            toggleButtonText(btnDrinks, "Beverages",50)
            refreshUI()
        }

        search.addTextChangedListener(object: TextWatcher{

            override fun afterTextChanged(s: Editable?) {
                val query = s.toString().trim().lowercase()
                pizza.visibility =
                    if(tvPizza.text.toString().lowercase().contains(query))
                        View.VISIBLE
                else
                        View.GONE

                bread.visibility =
                    if(tvBread.text.toString().lowercase().contains(query))
                        View.VISIBLE
                    else
                        View.GONE

                pizzaCombo.visibility =
                    if(tvPizzaCombo.text.toString().lowercase().contains(query))
                        View.VISIBLE
                    else
                        View.GONE

                breadCombo.visibility =
                    if(tvBreadCombo.text.toString().lowercase().contains(query))
                        View.VISIBLE
                    else
                        View.GONE

                drinks.visibility =
                    if(tvDrinks.text.toString().lowercase().contains(query))
                        View.VISIBLE
                    else
                        View.GONE

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })

        btnOrder.setOnClickListener{
            findNavController().navigate(R.id.action_pizza_to_order)
        }

    }

        }

