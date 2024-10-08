package com.example.recipeapp.recipe.home.view

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import com.example.recipeapp.R
import com.example.recipeapp.auth.login.view.AuthActivity
import com.example.recipeapp.auth.login.view.IS_LOGIN
import com.example.recipeapp.auth.login.view.userSharedPreferences
import com.google.android.material.bottomnavigation.BottomNavigationView

class RecipeActivity : AppCompatActivity() {
    private lateinit var bottomNav: BottomNavigationView
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the windowSoftInputMode programmatically
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        setContentView(R.layout.app_bar_main)

        toolbar = findViewById(R.id.toolbar)
        bottomNav = findViewById(R.id.buttom_navigation)

        //toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        bottomNav.setOnItemSelectedListener { menuItem ->
            val navController = findNavController(R.id.nav_host_fragment)

            when (menuItem.itemId) {
                R.id.home -> {
                    navController.navigate(R.id.homeFragment)
                    true
                }

                R.id.search -> {
                    navController.navigate(R.id.searchFragment)
                    true
                }

                R.id.favorite -> {
                    navController.navigate(R.id.favoriteFragment2)
                    true
                }

                R.id.profile -> {
                    navController.navigate(R.id.profileFragment)
                    true
                }

                else -> false
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about -> {
                findNavController(R.id.nav_host_fragment).navigate(R.id.aboutFragment)
                return true
            }

            R.id.sigOut -> {
                userSharedPreferences.edit().putBoolean(IS_LOGIN, false).apply()
                startActivity(Intent(this, AuthActivity::class.java))
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
