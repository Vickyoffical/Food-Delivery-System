package com.example.fds2

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class RestaurantMenu4 : Fragment() {
    override fun onCreateView(i: LayoutInflater, c: ViewGroup?, s: Bundle?) =
        i.inflate(R.layout.activity_restaurant_menu4, c, false)

    private lateinit var search4: EditText
    private lateinit var tvDosa: TextView
    private lateinit var tvIdli: TextView
    private lateinit var tvVada: TextView
    private lateinit var tvMeal: TextView
    private lateinit var tvDrinks4: TextView
    private lateinit var dosa: CardView
    private lateinit var idli: CardView
    private lateinit var vada: CardView
    private lateinit var meal: CardView
    private lateinit var drinks4: CardView
    private lateinit var btnDosa: Button
    private lateinit var btnIdli: Button
    private lateinit var btnVada: Button
    private lateinit var btnMeal: Button
    private lateinit var btnDrinks4: Button
    private lateinit var btnOrder4: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        search4 = view.findViewById(R.id.search4)
        tvDosa = view.findViewById(R.id.dosa)
        dosa = view.findViewById(R.id.dosaCard)
        tvIdli = view.findViewById(R.id.idli)
        idli = view.findViewById(R.id.idliCard)
        tvVada = view.findViewById(R.id.vada)
        vada = view.findViewById(R.id.vadaCard)
        tvMeal = view.findViewById(R.id.meal)
        meal = view.findViewById(R.id.mealCard)
        tvDrinks4 = view.findViewById(R.id.drinks4)
        drinks4 = view.findViewById(R.id.drinksCard4)
        btnDosa = view.findViewById(R.id.addDosa)
        btnIdli = view.findViewById(R.id.addIdli)
        btnVada = view.findViewById(R.id.addVada)
        btnMeal = view.findViewById(R.id.addMeal)
        btnDrinks4 = view.findViewById(R.id.addDrinks4)
        btnOrder4 = view.findViewById(R.id.btnOrder4)

        fun refreshUI(){
            btnDosa.text = if (CartManager.cartItems.containsKey("Dosa")) "Remove" else "Add"
            btnIdli.text = if (CartManager.cartItems.containsKey("Idli")) "Remove" else "Add"
            btnVada.text = if (CartManager.cartItems.containsKey("Medu Vada")) "Remove" else "Add"
            btnMeal.text = if (CartManager.cartItems.containsKey("Veg Rice Meal")) "Remove" else "Add"
            btnDrinks4.text = if (CartManager.cartItems.containsKey("Tea & Coffee")) "Remove" else "Add"

            btnOrder4.visibility = if(CartManager.cartItems.isNotEmpty()) View.VISIBLE else View.GONE
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

        btnOrder4.visibility = if(CartManager.cartItems.isNotEmpty()) View.VISIBLE else View.GONE

        refreshUI()

        btnDosa.setOnClickListener {
            toggleButtonText(btnDosa, "Dosa", 50)
            refreshUI()
        }

        btnIdli.setOnClickListener {
            toggleButtonText(btnIdli, "Idli", 30)
            refreshUI()
        }

        btnVada.setOnClickListener {
            toggleButtonText(btnVada, "Medu Vada", 30)
            refreshUI()
        }

        btnMeal.setOnClickListener {
            toggleButtonText(btnMeal, "Veg Rice Meal", 120)
            refreshUI()
        }

        btnDrinks4.setOnClickListener {
            toggleButtonText(btnDrinks4, "Tea & Coffee", 90)
            refreshUI()
        }

        search4.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {
                val query = s.toString().trim().lowercase()
                dosa.visibility =
                    if (tvDosa.text.toString().lowercase().contains(query))
                        View.VISIBLE
                    else
                        View.GONE

                idli.visibility =
                    if (tvIdli.text.toString().lowercase().contains(query))
                        View.VISIBLE
                    else
                        View.GONE

                vada.visibility =
                    if (tvVada.text.toString().lowercase().contains(query))
                        View.VISIBLE
                    else
                        View.GONE

                meal.visibility =
                    if (tvMeal.text.toString().lowercase().contains(query))
                        View.VISIBLE
                    else
                        View.GONE

                drinks4.visibility =
                    if (tvDrinks4.text.toString().lowercase().contains(query))
                        View.VISIBLE
                    else
                        View.GONE

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })

        btnOrder4.setOnClickListener{
            findNavController().navigate(R.id.action_south_to_order)
        }

    }
}