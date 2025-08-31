package com.example.fds2

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fds2.database.DatabaseHelper
import com.google.android.material.tabs.TabLayout

class HomeFragment : Fragment() {
    override fun onCreateView(i: LayoutInflater, c: ViewGroup?, s: Bundle?) =
        i.inflate(R.layout.fragment_home, c, false)

        private lateinit var tabLayout: TabLayout
        private lateinit var tvPizza: TextView
        private lateinit var tvBurger: TextView
        private lateinit var tvChinese: TextView
        private lateinit var tvSouth: TextView
        private lateinit var pizza: CardView
        private lateinit var burger: CardView
        private lateinit var chinese: CardView
        private lateinit var south: CardView
        private lateinit var search: EditText

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tabLayout = view.findViewById(R.id.tabLayout)
        tvPizza = view.findViewById(R.id.tvPizza)
        tvBurger = view.findViewById(R.id.tvBurger)
        tvChinese = view.findViewById(R.id.tvChinese)
        tvSouth = view.findViewById(R.id.tvSouth)
        pizza = view.findViewById(R.id.restaurantCard)
        burger = view.findViewById(R.id.burgerCard)
        chinese = view.findViewById(R.id.chineseCard)
        south = view.findViewById(R.id.southCard)
        search = view.findViewById(R.id.resSearch)

        search.addTextChangedListener(object: TextWatcher {

            override fun afterTextChanged(s: Editable?) {
                val query = s.toString().trim().lowercase()
                pizza.visibility =
                    if(tvPizza.text.toString().lowercase().contains(query))
                        View.VISIBLE
                    else
                        View.GONE

                burger.visibility =
                    if(tvBurger.text.toString().lowercase().contains(query))
                        View.VISIBLE
                    else
                        View.GONE

                chinese.visibility =
                    if(tvChinese.text.toString().lowercase().contains(query))
                        View.VISIBLE
                    else
                        View.GONE

                south.visibility =
                    if(tvSouth.text.toString().lowercase().contains(query))
                        View.VISIBLE
                    else
                        View.GONE

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position) {
                    0 -> showExplore()
                    1 -> showTopRated()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            private fun showExplore() {
                pizza.visibility = View.VISIBLE
                burger.visibility = View.VISIBLE
                chinese.visibility = View.VISIBLE
                south.visibility = View.VISIBLE
            }

            private fun showTopRated() {
                pizza.visibility = View.VISIBLE
                burger.visibility = View.GONE
                chinese.visibility = View.GONE
                south.visibility = View.VISIBLE
            }

        })

        pizza.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_res1)
        }

        burger.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_res2)
        }

        chinese.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_res3)
        }

        south.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_res4)
        }

        val dbHelper = DatabaseHelper(requireContext())

        if (dbHelper.getAllRestaurants().isEmpty()) {
            dbHelper.insertRestaurant("Pizza Hut", "Pizza", 4.5)
            dbHelper.insertRestaurant("Burger King", "Burger", 4.2)
            dbHelper.insertRestaurant("Dragon Chinese", "Chinese", 4.0)
            dbHelper.insertRestaurant("South Spice", "South Indian", 4.3)
        }

        val restaurants = dbHelper.getAllRestaurants()

        tvPizza.text = restaurants.find { it.category == "Pizza" }?.name ?: "Pizza"
        tvBurger.text = restaurants.find { it.category == "Burger" }?.name ?: "Burger"
        tvChinese.text = restaurants.find { it.category == "Chinese" }?.name ?: "Chinese"
        tvSouth.text = restaurants.find { it.category == "South Indian" }?.name ?: "South"


    }

}
