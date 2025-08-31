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

class RestaurantMenu2 : Fragment() {

    override fun onCreateView(i: LayoutInflater, c: ViewGroup?, s: Bundle?) =
        i.inflate(R.layout.activity_restaurant_menu2, c, false)

    private lateinit var search2: EditText
    private lateinit var tvBurger: TextView
    private lateinit var tvWrap: TextView
    private lateinit var tvFries: TextView
    private lateinit var tvCombo: TextView
    private lateinit var tvDrinks2: TextView
    private lateinit var burger: CardView
    private lateinit var wrap: CardView
    private lateinit var fries: CardView
    private lateinit var combo: CardView
    private lateinit var drinks2: CardView
    private lateinit var btnBurger: Button
    private lateinit var btnWrap: Button
    private lateinit var btnFries: Button
    private lateinit var btnCombo: Button
    private lateinit var btnDrinks2: Button
    private lateinit var btnOrder2: Button
    private var secondcartCount = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        search2 = view.findViewById(R.id.search2)
        tvBurger = view.findViewById(R.id.burger)
        burger = view.findViewById(R.id.burgerCard)
        tvWrap = view.findViewById(R.id.wraps)
        wrap = view.findViewById(R.id.wrapCard)
        tvFries = view.findViewById(R.id.fries)
        fries = view.findViewById(R.id.friesCard)
        tvCombo = view.findViewById(R.id.combo)
        combo = view.findViewById(R.id.comboCard)
        tvDrinks2 = view.findViewById(R.id.drinks2)
        drinks2 = view.findViewById(R.id.drinksCard2)
        btnBurger = view.findViewById(R.id.addBurger)
        btnWrap = view.findViewById(R.id.addWrap)
        btnFries = view.findViewById(R.id.addFries)
        btnCombo = view.findViewById(R.id.addCombo)
        btnDrinks2 = view.findViewById(R.id.addDrinks2)
        btnOrder2 = view.findViewById(R.id.btnOrder2)

        fun refreshUI(){
            btnBurger.text = if (CartManager.cartItems.containsKey("Burger")) "Remove" else "Add"
            btnWrap.text = if (CartManager.cartItems.containsKey("Signature Wrap")) "Remove" else "Add"
            btnFries.text = if (CartManager.cartItems.containsKey("Fries")) "Remove" else "Add"
            btnCombo.text = if (CartManager.cartItems.containsKey("Full Combo")) "Remove" else "Add"
            btnDrinks2.text = if (CartManager.cartItems.containsKey("Beverages")) "Remove" else "Add"

            btnOrder2.visibility = if(CartManager.cartItems.isNotEmpty()) View.VISIBLE else View.GONE
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

        btnOrder2.visibility = if(CartManager.cartItems.isNotEmpty()) View.VISIBLE else View.GONE

        refreshUI()

        btnBurger.setOnClickListener {
            toggleButtonText(btnBurger, "Burger", 80)
            refreshUI()
        }

        btnWrap.setOnClickListener {
            toggleButtonText(btnWrap, "Signature Wrap", 100)
            refreshUI()
        }

        btnFries.setOnClickListener {
            toggleButtonText(btnFries, "Fries", 60)
            refreshUI()
        }

        btnCombo.setOnClickListener {
            toggleButtonText(btnCombo, "Full Combo", 240)
            refreshUI()
        }

        btnDrinks2.setOnClickListener {
            toggleButtonText(btnDrinks2, "Beverages", 90)
            refreshUI()
        }

        search2.addTextChangedListener(object: TextWatcher {

            override fun afterTextChanged(s: Editable?) {
                val query = s.toString().trim().lowercase()
                burger.visibility =
                    if(tvBurger.text.toString().lowercase().contains(query))
                        View.VISIBLE
                    else
                        View.GONE

                wrap.visibility =
                    if(tvWrap.text.toString().lowercase().contains(query))
                        View.VISIBLE
                    else
                        View.GONE

                fries.visibility =
                    if(tvFries.text.toString().lowercase().contains(query))
                        View.VISIBLE
                    else
                        View.GONE

                combo.visibility =
                    if(tvCombo.text.toString().lowercase().contains(query))
                        View.VISIBLE
                    else
                        View.GONE

                drinks2.visibility =
                    if(tvDrinks2.text.toString().lowercase().contains(query))
                        View.VISIBLE
                    else
                        View.GONE

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })

        btnOrder2.setOnClickListener{
            findNavController().navigate(R.id.action_burger_to_order)
        }

    }


}