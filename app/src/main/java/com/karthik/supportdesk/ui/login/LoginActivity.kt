package com.karthik.supportdesk.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.viewModels
import com.karthik.supportdesk.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_login.*

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setUIActions()
        setInitObservers()
    }

    private fun setUIActions() {
        loginEditText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                loginInputLayout.error = null
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        loginEditText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                loginInputLayout.error = null
            } else {
                loginViewModel.validateUrl(loginEditText.text.toString())
            }
        }

        nextButton.setOnClickListener {
            loginViewModel.nextTapped(loginEditText.text.toString())
        }

    }

    private fun setInitObservers() {
        loginViewModel.alert.observe(this@LoginActivity) {
            it.getContentIfNotHandled().let { error ->
                when (error) {
                    is LoginViewModel.Events.Alert.EmailInvalid -> {
                        loginInputLayout.error = error.message
                    }
                }
            }
        }

        loginViewModel.navigate.observe(this@LoginActivity) {
            it.getContentIfNotHandled().let { navigation ->
                when(navigation) {
                    LoginViewModel.Events.Navigation.OpenTicketScreen -> Toast.makeText(this@LoginActivity, "Login Success", Toast.LENGTH_LONG).show()
                }
            }
        }
    }


}