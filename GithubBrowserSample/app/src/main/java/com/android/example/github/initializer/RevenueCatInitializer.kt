package com.android.example.github.initializer

import android.content.Context
import androidx.startup.Initializer
import com.onesignal.BuildConfig
import com.revenuecat.purchases.Purchases

class RevenueCatInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        if (BuildConfig.DEBUG) {
            Purchases.debugLogsEnabled = true
        }
        Purchases.configure(context, revenueCatPublicSdkKey)
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()

    companion object {
        const val revenueCatPublicSdkKey = "###############"
    }
}