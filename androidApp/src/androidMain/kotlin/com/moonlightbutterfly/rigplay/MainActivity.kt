package com.moonlightbutterfly.rigplay

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.moonlightbutterfly.rigplay.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        supportFragmentManager.commit {
            replace(binding.navHostFragment.id, GameListFragment())
            setReorderingAllowed(true)
            addToBackStack(null)
        }
        setContentView(binding.root)
    }
}