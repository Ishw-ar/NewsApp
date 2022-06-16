package com.varsha.newsapp.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
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
            loginViewModel.loginUser()
        }
    }

    private fun subscribeToObservables() {
        loginViewModel.loginResponse.observe(this, Observer { response ->
            when (response) {
                is NetworkResult.Success -> {
                    Toast.makeText(this, "Login Successfully", Toast.LENGTH_SHORT).show()
                    navigateToDashboard()
                }
                is NetworkResult.Loading -> {

                }
                is NetworkResult.Error -> {
                    Toast.makeText(this, response.message, Toast.LENGTH_SHORT).show()
                }
            }
        })

    }

    private fun navigateToDashboard() {
        val intent = Intent(this@LoginActivity, DashBoardActivity::class.java)
        startActivity(intent)
    }
}