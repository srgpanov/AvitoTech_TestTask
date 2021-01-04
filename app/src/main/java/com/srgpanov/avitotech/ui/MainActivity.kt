package com.srgpanov.avitotech.ui;

import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import com.srgpanov.avitotech.R
import com.srgpanov.avitotech.other.getColorCompat
import com.srgpanov.avitotech.ui.screens.number_list.NumberFragment
import com.srgpanov.avitotech.ui.screens.number_list.NumberFragment.Companion.TAG

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEdgeToEdge()
        if (supportFragmentManager.findFragmentByTag(TAG) == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, NumberFragment(),TAG)
                .commit()
        }
    }

    private fun setupEdgeToEdge() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
            window.insetsController?.let {
                it.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
                it.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else {
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
        setStatusBarColor()
    }

    private fun setStatusBarColor() {
        if (Build.VERSION.SDK_INT < 23) {
            val window: Window = window
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

            window.statusBarColor = getColorCompat(R.color.white)
        }
    }
}
