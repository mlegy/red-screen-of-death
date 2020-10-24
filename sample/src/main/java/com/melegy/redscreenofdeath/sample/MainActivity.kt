package com.melegy.redscreenofdeath.sample

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.LinearLayout.LayoutParams
import androidx.appcompat.app.AppCompatActivity

@SuppressLint("SetTextI18n")
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val params = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        LinearLayout(this).apply {
            gravity = Gravity.CENTER
            addView(Button(this@MainActivity).apply {
                text = "Crash me"
                setOnClickListener {
                    throw RuntimeException("This is a test crash")
                }
            }, params)
        }.also { setContentView(it) }
    }
}
