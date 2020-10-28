package com.melegy.redscreenofdeath

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.Intent.*
import android.os.Build
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.melegy.redscreenofdeath.databinding.ActivityRedScreenOfDeathBinding

@SuppressLint("SetTextI18n")
internal class RedScreenOfDeathActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRedScreenOfDeathBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRedScreenOfDeathBinding.inflate(layoutInflater)

        supportActionBar?.hide()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = ContextCompat.getColor(this, R.color.red)
            window.navigationBarColor = ContextCompat.getColor(this, R.color.red)
        }
        setContentView(binding.root)

        val threadName = requireNotNull(intent.getStringExtra(EXTRA_THREAD))
        val throwable = intent.getSerializableExtra(EXTRA_THROWABLE) as Throwable

        renderException(threadName, throwable)
        logException(throwable)
    }

    private fun renderException(threadName: String, throwable: Throwable) {
        binding.textThreadName.text = "App crashed in $threadName thread"
        binding.textException.text = throwable.javaClass.simpleName
        binding.textStackTrace.text = throwable.stackTraceToString()
        binding.textStackTrace.movementMethod = ScrollingMovementMethod()
    }

    private fun logException(throwable: Throwable) {
        Logger.logger.e("═══════════ Exception caught by Red Screen Of Death library ═══════════")
        Logger.logger.e(throwable.javaClass.simpleName, throwable)
        Logger.logger.e("════════════════════════════════════════════════════════════════════")
    }

    companion object {
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
