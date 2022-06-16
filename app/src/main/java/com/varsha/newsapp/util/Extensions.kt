package com.varsha.newsapp.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

fun FragmentActivity.addFragment(containerId: Int, fragment: Fragment) {
    val manager = supportFragmentManager
    val transaction = manager.beginTransaction()
    transaction.add(containerId, fragment, fragment::class.java.simpleName)
        .addToBackStack(fragment.javaClass.simpleName)
    transaction.commit()
}