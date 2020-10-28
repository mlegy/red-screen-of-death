package com.melegy.redscreenofdeath.internal

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
import com.melegy.redscreenofdeath.R
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
        setupShareButton(threadName, throwable)
        logException(throwable)
    }

    private fun renderException(threadName: String, throwable: Throwable) {
        binding.textThreadName.text = "App crashed in $threadName thread"
        binding.textException.text = throwable.javaClass.simpleName
        binding.textStackTrace.text = throwable.stackTraceToString()
        binding.textStackTrace.movementMethod = ScrollingMovementMethod()
    }

    private fun setupShareButton(threadName: String, throwable: Throwable) {
        val appDate = requireNotNull(intent.getParcelableExtra<AppDate>(EXTRA_APP_DATA))

        binding.shareButton.setOnClickListener {
            val sendIntent = Intent().apply {
                action = ACTION_SEND
                putExtra(
                    EXTRA_TEXT,
                    Utils.generateTextToShare(appDate, threadName, throwable)
                )
                type = "text/plain"
            }
            val shareIntent = createChooser(sendIntent, null)
            startActivity(shareIntent)
        }
    }

    private fun logException(throwable: Throwable) {
        Logger.logger.e("══════════ Exception caught by Red Screen Of Death library ═════════")
        Logger.logger.e(throwable.javaClass.simpleName, throwable)
        Logger.logger.e("════════════════════════════════════════════════════════════════════")
    }

    companion object {
        private const val EXTRA_THREAD = "com.melegy.redscreenofdeath.EXTRA_THREAD"
        private const val EXTRA_THROWABLE = "com.melegy.redscreenofdeath.EXTRA_THROWABLE"
        private const val EXTRA_APP_DATA = "com.melegy.redscreenofdeath.EXTRA_APP_DATA"

        fun newIntent(
            context: Context,
            threadName: String,
            throwable: Throwable,
            appData: AppDate,
        ) = Intent(context, RedScreenOfDeathActivity::class.java)
            .apply {
                putExtra(EXTRA_THREAD, threadName)
                putExtra(EXTRA_THROWABLE, throwable)
                putExtra(EXTRA_APP_DATA, appData)
                flags =
                    FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TASK or FLAG_ACTIVITY_NO_ANIMATION
            }
    }
}
