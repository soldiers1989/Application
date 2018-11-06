package com.chad.player.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.chad.player.R

class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.simpleName.toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}