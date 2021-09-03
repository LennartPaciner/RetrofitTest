package com.example.retrofittest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofittest.databinding.ActivityMainBinding
import com.example.retrofittest.ui.GetFragment
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.HttpException
import java.io.IOException

/*
TODO navigation component raffen mit fragments und activity und andere aufrufen

TODO stand von dem crash kurs in fragment auslagern mittels nav graph navigieren hin und zurück

TODO post request machen (falls die api das zulässt) und schauen obs geht mit 2ten button

TODO mvvm pattern draus machen und schauen dass es noch geht

TODO viewbinding versuchen zu verstehen

TODO bottom navigation einbauen für die verschiedenen requests

TODO put und delete noch einbauen falls die das unterstützen

TODO konzepte verwenden/lernen/anschauen: view binding (hier schon drin), dependy injection mit hilt, nav graph, room
 */

//app mit view binding für layouts, retrofit aufrufen an rest api, toolbar custom, fragment nav graph, safeargs für fragmente, ...
class MainActivity : AppCompatActivity() {

    //nach mvvm: aufruf in repo machen von der api und das in nem viewmodel aufrufen TODO
    //viewbinding
    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //toolbar stuff
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.findNavController()

        setSupportActionBar(toolbar)
        setupActionBarWithNavController(navController)

        //bottomview
        binding.bottomNav.setupWithNavController(navController)
        navHostFragment.findNavController().addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id) {
                R.id.getFragment, R.id.postFragment, R.id.startFragment -> binding.bottomNav.visibility = View.VISIBLE
                else -> binding.bottomNav.visibility = View.INVISIBLE
            }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount
        if(count == 0) {
            super.onBackPressed()
        } else {
            supportFragmentManager.popBackStack()
        }

    }


}

























