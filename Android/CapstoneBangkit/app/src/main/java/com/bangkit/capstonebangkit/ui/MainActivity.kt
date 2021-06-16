package com.bangkit.capstonebangkit.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.bangkit.capstonebangkit.R
import com.bangkit.capstonebangkit.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    private val rotateOpen: Animation by lazy { AnimationUtils.loadAnimation(this,
        R.anim.rotate_open_anim
    ) }
    private val rotateClose: Animation by lazy { AnimationUtils.loadAnimation(this,
        R.anim.rotate_close_anim
    ) }
    private val fromBottom: Animation by lazy { AnimationUtils.loadAnimation(this,
        R.anim.from_botton_anim
    ) }
    private val toBottom: Animation by lazy { AnimationUtils.loadAnimation(this,
        R.anim.to_botton_anim
    ) }
    private var clicked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        mainBinding.apply {
            floatingActionButton.setOnClickListener {
                onAddButtomClicked()
            }

            floatingButtonHelp.setOnClickListener {
                val intent = Intent(this@MainActivity, HelpActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun onAddButtomClicked() {
        setVisibility(clicked)
        setAnimation(clicked)
        clicked = !clicked
    }

    private fun setVisibility(clicked: Boolean) {
        if(!clicked){
            mainBinding.apply {
                floatingButtonHelp.visibility = View.VISIBLE
            }
        } else {
            mainBinding.apply {
                floatingButtonHelp.visibility = View.INVISIBLE
            }
        }
    }

    private fun setAnimation(clicked: Boolean) {
        if (!clicked){
            mainBinding.apply {
                floatingButtonHelp.startAnimation(fromBottom)
                floatingActionButton.startAnimation(rotateOpen)
            }
        } else {
            mainBinding.apply {
                floatingButtonHelp.startAnimation(toBottom)
                floatingActionButton.startAnimation(rotateClose)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_menu -> {
                val mFav = Intent(this, AboutActivity::class.java)
                startActivity(mFav)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}