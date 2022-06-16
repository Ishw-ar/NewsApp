package com.varsha.newsapp.ui.dashboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.varsha.newsapp.databinding.ActivityDashBoardBinding
import com.varsha.newsapp.util.addFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashBoardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashBoardBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val fragment = NewsFragment()
        addFragment(binding.flContainer.id, fragment)
    }
}