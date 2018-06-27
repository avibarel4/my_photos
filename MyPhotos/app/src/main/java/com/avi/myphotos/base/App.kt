package com.avi.myphotos.base

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.os.Handler
import android.os.Looper

/**
 * Created by avi.barel on 27/06/2018.
 */
class App : Application() {

    companion object {
        private const val DISABLE_TOUCH_DEFAULT_DURATION: Int = 300

        private var touchEnabled = true
        private val touchHandler = Handler()
        private val touchRunnable = Runnable { touchEnabled = true }

        val mainHandler = Handler(Looper.getMainLooper())

        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
            private set

        fun isTouchEnabled(): Boolean {
            return touchEnabled
        }

        fun disableTouchEventForDefaultDuration() {
            disableTouchEvent(DISABLE_TOUCH_DEFAULT_DURATION)
        }

        fun disableTouchEvent(duration: Int) {
            touchHandler.removeCallbacks(touchRunnable)
            touchEnabled = false
            touchHandler.postDelayed(touchRunnable, duration.toLong())
        }
    }

    override fun onCreate() {
        super.onCreate()

        context = applicationContext
    }

}