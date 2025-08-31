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
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class LoginFragment : Fragment() {
    override fun onCreateView(i: LayoutInflater, c: ViewGroup?, s: Bundle?) =
        i.inflate(R.layout.fragment_login, c, false)


    private lateinit var loginUsername: EditText
    private lateinit var loginPassword: EditText
    private lateinit var loginButton: Button
    private lateinit var loginTV: TextView

    override fun onViewCreated(v: View, s: Bundle?) {

        loginUsername = v.findViewById(R.id.edit_username)
        loginPassword = v.findViewById(R.id.edit_mobile)
        loginButton = v.findViewById(R.id.loginBtn)
        loginTV = v.findViewById(R.id.loginTV)

        loginButton.isEnabled = false

        var textWatcher = object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {
                val username = loginUsername.text.toString().trim()
                val password = loginPassword.text.toString().trim()

                loginButton.isEnabled = username.isNotEmpty() && password.isNotEmpty()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }

        loginUsername.addTextChangedListener(textWatcher)
        loginPassword.addTextChangedListener(textWatcher)

        loginButton.setOnClickListener {
            findNavController().navigate(R.id.action_login_to_home)
        }
        loginTV.setOnClickListener{
            findNavController().navigate(R.id.action_login_to_signup)
        }
    }
    }
