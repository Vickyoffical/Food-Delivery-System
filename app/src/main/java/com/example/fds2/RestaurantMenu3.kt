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

class RestaurantMenu3 : Fragment() {
    override fun onCreateView(i: LayoutInflater, c: ViewGroup?, s: Bundle?) =
        i.inflate(R.layout.activity_restaurant_menu3, c, false)

    private lateinit var search3: EditText
    private lateinit var tvManchurian: TextView
    private lateinit var tvPaneer: TextView
    private lateinit var tvNoodles: TextView
    private lateinit var tvRice: TextView
    private lateinit var tvDrinks3: TextView
    private lateinit var manchurian: CardView
    private lateinit var paneer: CardView
    private lateinit var noodles: CardView
    private lateinit var rice: CardView
    private lateinit var drinks3: CardView
    private lateinit var btnManchurian: Button
    private lateinit var btnPaneer: Button
    private lateinit var btnNoodles: Button
    private lateinit var btnRice: Button
    private lateinit var btnDrinks3: Button
    private lateinit var btnOrder3: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        search3 = view.findViewById(R.id.search3)
        tvManchurian = view.findViewById(R.id.manchurian)
        manchurian = view.findViewById(R.id.manchurianCard)
        tvPaneer = view.findViewById(R.id.paneerChilly)
        paneer = view.findViewById(R.id.paneerCard)
        tvNoodles = view.findViewById(R.id.noodles)
        noodles = view.findViewById(R.id.noodlesCard)
        tvRice = view.findViewById(R.id.rice)
        rice = view.findViewById(R.id.riceCard)
        tvDrinks3 = view.findViewById(R.id.drinks3)
        drinks3 = view.findViewById(R.id.drinksCard3)
        btnManchurian = view.findViewById(R.id.addManchurian)
        btnPaneer = view.findViewById(R.id.addPaneer)
        btnNoodles = view.findViewById(R.id.addNoodles)
        btnRice = view.findViewById(R.id.addRice)
        btnDrinks3 = view.findViewById(R.id.addDrinks3)
        btnOrder3 = view.findViewById(R.id.btnOrder3)

        fun refreshUI(){
            btnManchurian.text = if (CartManager.cartItems.containsKey("Manchurian")) "Remove" else "Add"
            btnPaneer.text = if (CartManager.cartItems.containsKey("Paneer Chilly")) "Remove" else "Add"
            btnNoodles.text = if (CartManager.cartItems.containsKey("Veg Noodles")) "Remove" else "Add"
            btnRice.text = if (CartManager.cartItems.containsKey("Fried Rice")) "Remove" else "Add"
            btnDrinks3.text = if (CartManager.cartItems.containsKey("Beverages")) "Remove" else "Add"

            btnOrder3.visibility = if(CartManager.cartItems.isNotEmpty()) View.VISIBLE else View.GONE
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

        btnOrder3.visibility = if(CartManager.cartItems.isNotEmpty()) View.VISIBLE else View.GONE

        refreshUI()

        btnManchurian.setOnClickListener {
            toggleButtonText(btnManchurian, "Manchurian", 80)
            refreshUI()
        }

        btnPaneer.setOnClickListener {
            toggleButtonText(btnPaneer, "Paneer Chilly", 80)
            refreshUI()
        }

        btnNoodles.setOnClickListener {
            toggleButtonText(btnNoodles, "Veg Noodles", 90)
            refreshUI()
        }

        btnRice.setOnClickListener {
            toggleButtonText(btnRice, "Fried Rice", 90)
            refreshUI()
        }

        btnDrinks3.setOnClickListener {
            toggleButtonText(btnDrinks3, "Beverages", 100)
            refreshUI()
        }

        search3.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {
                val query = s.toString().trim().lowercase()
                manchurian.visibility =
                    if (tvManchurian.text.toString().lowercase().contains(query))
                        View.VISIBLE
                    else
                        View.GONE

                paneer.visibility =
                    if (tvPaneer.text.toString().lowercase().contains(query))
                        View.VISIBLE
                    else
                        View.GONE

                noodles.visibility =
                    if (tvNoodles.text.toString().lowercase().contains(query))
                        View.VISIBLE
                    else
                        View.GONE

                rice.visibility =
                    if (tvRice.text.toString().lowercase().contains(query))
                        View.VISIBLE
                    else
                        View.GONE

                drinks3.visibility =
                    if (tvDrinks3.text.toString().lowercase().contains(query))
                        View.VISIBLE
                    else
                        View.GONE

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })

        btnOrder3.setOnClickListener{
            findNavController().navigate(R.id.action_chinese_to_order)
        }

    }

}