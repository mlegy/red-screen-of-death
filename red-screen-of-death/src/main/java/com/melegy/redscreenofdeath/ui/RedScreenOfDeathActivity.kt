package com.melegy.redscreenofdeath.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.Intent.*
import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.TypedValue
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.LinearLayout.LayoutParams
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.melegy.redscreenofdeath.logger.Logger


@SuppressLint("SetTextI18n")
internal class RedScreenOfDeathActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = red
            window.navigationBarColor = red
        }

        val threadName = requireNotNull(intent.getStringExtra(EXTRA_THREAD))
        val throwable = intent.getSerializableExtra(EXTRA_THROWABLE) as Throwable

        LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            val padding = convertDpToPx(16f)
            setPadding(padding, padding, padding, padding)
            setBackgroundColor(red)

            val params = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
            addView(TextView(this@RedScreenOfDeathActivity).apply {
                setDefaultTextViewStyle()
                setTypeface(typeface, Typeface.BOLD)
                textSize = 18f
                text = "App crashed in $threadName thread"
            }, params)

            addView(TextView(this@RedScreenOfDeathActivity).apply {
                setDefaultTextViewStyle()
                setTypeface(typeface, Typeface.BOLD)
                textSize = 14f
                setPadding(0, convertDpToPx(16f), 0, 0)
                text = throwable.javaClass.simpleName
            }, params)

            addView(TextView(this@RedScreenOfDeathActivity).apply {
                setDefaultTextViewStyle()
                textSize = 14f
                isVerticalScrollBarEnabled = true
                movementMethod = ScrollingMovementMethod()
                setPadding(0, convertDpToPx(8f), 0, 0)
                text = throwable.stackTraceToString()
            }, params)
        }.also { setContentView(it) }

        Logger.logger.e("═══════════ Exception caught by RedScreenOfDeath library ═══════════")
        Logger.logger.e(throwable.javaClass.simpleName, throwable)
        Logger.logger.e("════════════════════════════════════════════════════════════════════")
    }

    private fun TextView.setDefaultTextViewStyle() = also {
        typeface = Typeface.MONOSPACE
        setTextColor(Color.parseColor("#FFFF66"))
    }

    private fun Activity.convertDpToPx(dp: Float) =
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics).toInt()

    companion object {
        private val red = Color.parseColor("#880000")

        private const val EXTRA_THREAD = "com.melegy.redscreenofdeath.EXTRA_THREAD"
        private const val EXTRA_THROWABLE = "com.melegy.redscreenofdeath.EXTRA_THROWABLE"

        fun newIntent(
            context: Context,
            threadName: String,
            throwable: Throwable,
        ) = Intent(context, RedScreenOfDeathActivity::class.java)
            .apply {
                putExtra(EXTRA_THREAD, threadName)
                putExtra(EXTRA_THROWABLE, throwable)
                flags =
                    FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TASK or FLAG_ACTIVITY_NO_ANIMATION
            }
    }
}
