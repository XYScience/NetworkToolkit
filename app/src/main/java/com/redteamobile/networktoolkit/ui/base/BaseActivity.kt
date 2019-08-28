package com.redteamobile.networktoolkit.ui.base

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

/**
 * @author SScience
 * @description
 * @email chentushen.science@gmail.com
 * @data 2019-08-28
 */
open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initSystemUi()
    }

    private fun initSystemUi() {

        val decorView = window.decorView
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            var suv = decorView.systemUiVisibility
            suv = suv or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            decorView.systemUiVisibility = suv
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            var suv = decorView.systemUiVisibility
            suv = suv or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
            decorView.systemUiVisibility = suv
            window.navigationBarColor = getColor(android.R.color.transparent)
        }
    }
}