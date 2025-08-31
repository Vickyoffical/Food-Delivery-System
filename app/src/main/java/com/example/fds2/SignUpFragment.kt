package com.example.fds2

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.fragment.findNavController

class SignUpFragment : Fragment() {
        override fun onCreateView(i: LayoutInflater, c: ViewGroup?, s: Bundle?) =
            i.inflate(R.layout.fragment_sign_up, c, false)

    private lateinit var signupUsername: EditText
    private lateinit var signupPhone: EditText
    private lateinit var signupEmail: EditText
    private lateinit var signupButton: Button
    private lateinit var signupTV: TextView

    override fun onViewCreated(v: View, s: Bundle?) {

        signupUsername = v.findViewById(R.id.edit_name)
        signupPhone = v.findViewById(R.id.edit_mobile_num)
        signupEmail = v.findViewById(R.id.edit_email)
        signupButton = v.findViewById(R.id.signUpBtn)
        signupTV = v.findViewById(R.id.signUpTV)

        signupButton.isEnabled = false

        var textWatcher = object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {
                val name = signupUsername.text.toString().trim()
                val phone = signupPhone.text.toString().trim()
                val mail = signupEmail.text.toString().trim()

                signupButton.isEnabled = name.isNotEmpty() && phone.isNotEmpty() && mail.isNotEmpty()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }

        signupUsername.addTextChangedListener(textWatcher)
        signupPhone.addTextChangedListener(textWatcher)
        signupEmail.addTextChangedListener(textWatcher)

        signupButton.setOnClickListener {
            findNavController().navigate(R.id.action_signup_to_home)
        }

        signupTV.setOnClickListener{
            findNavController().navigate(R.id.action_signup_to_login)
        }
    }
}

