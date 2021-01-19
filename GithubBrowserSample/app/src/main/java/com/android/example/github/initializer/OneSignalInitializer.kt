package com.android.example.github.initializer

import android.content.Context
import androidx.startup.Initializer
import com.onesignal.BuildConfig
import com.onesignal.OneSignal

class OneSignalInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        if (BuildConfig.DEBUG) {
            OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)
        }
        // OneSignal Initialization
        OneSignal.initWithContext(context)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()

    companion object {
        const val ONESIGNAL_APP_ID = "########-####-####-####-############"
    }
}