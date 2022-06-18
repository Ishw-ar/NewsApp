package com.varsha.newsapp.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.varsha.newsapp.R
import com.varsha.newsapp.databinding.ActivityLoginBinding
import com.varsha.newsapp.ui.dashboard.DashBoardActivity
import com.varsha.newsapp.util.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val loginViewModel by viewModels<LoginViewModel>()
    private lateinit var binding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        clickEvents()
        subscribeToObservables()
    }

    private fun clickEvents() {
        binding.btnLogin.setOnClickListener {
            if (isValidUserName() && isValidPassword()) {
                loginViewModel.loginUser()
            }
        }
    }

    private fun subscribeToObservables() {
        loginViewModel.loginResponse.observe(this, Observer { response ->
            when (response) {
                is NetworkResult.Success -> {
                    Toast.makeText(this, "Login Successfully", Toast.LENGTH_SHORT).show()
                    binding.progressLogin.visibility = View.GONE
                    binding.btnLogin.visibility = View.VISIBLE
                    navigateToDashboard()
                }
                is NetworkResult.Loading -> {
                    binding.progressLogin.visibility = View.VISIBLE
                    binding.btnLogin.visibility = View.GONE
                }
                is NetworkResult.Error -> {
                    Toast.makeText(this, response.message, Toast.LENGTH_SHORT).show()
                    binding.progressLogin.visibility = View.GONE
                    binding.btnLogin.visibility = View.VISIBLE
                }
            }
        })

    }

    private fun navigateToDashboard() {
        val intent = Intent(this@LoginActivity, DashBoardActivity::class.java)
        startActivity(intent)
    }

    private fun isValidUserName(): Boolean {
        val isValid: Boolean

        when {
            binding.editTextUserName.text.toString().trim().isEmpty() -> {
                binding.layoutUserName.error =
                    getString(R.string.this_field_is_required)
                isValid = false
            }

            else -> {
                binding.layoutUserName.isErrorEnabled = false
                isValid = true
            }
        }

        return isValid
    }

    private fun isValidPassword(): Boolean {
        val isValid: Boolean

        when {
            binding.editTextPassword.text.toString().trim().isEmpty() -> {
                binding.layoutPassword.error =
                    getString(R.string.this_field_is_required)
                isValid = false
            }

            else -> {
                binding.layoutPassword.isErrorEnabled = false
                isValid = true
            }
        }

        return isValid
    }
}