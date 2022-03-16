package com.mindfire.pashupaltask.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.mindfire.pashupaltask.R
import com.mindfire.pashupaltask.fragment.NewsSourcesFragment
import com.mindfire.pashupaltask.fragment.RecyclerListFragment

class NewsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        setupFragment()
    }
    private fun setupFragment() {
        val fragment= RecyclerListFragment.newInstance()
        val fragmentManager: FragmentManager =supportFragmentManager
        val fragmentTransaction: FragmentTransaction =fragmentManager.beginTransaction()
        fragmentTransaction.replace(android.R.id.content,fragment)
        fragmentTransaction.commit()
    }
}