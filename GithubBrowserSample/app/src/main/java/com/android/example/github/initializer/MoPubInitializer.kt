package com.android.example.github.initializer

import android.content.Context
import androidx.startup.Initializer
import com.android.example.github.BuildConfig
import com.mopub.common.MoPub
import com.mopub.common.SdkConfiguration
import com.mopub.common.SdkInitializationListener
import com.mopub.common.logging.MoPubLog
import timber.log.Timber


class MoPubInitializer : Initializer<Unit> {

    private fun initSdkListener(): SdkInitializationListener? {
        return SdkInitializationListener {
            /* MoPub SDK initialized.
               Check if you should show the consent dialog here, and make your ad requests. */
            Timber.d("Timber: MoPub SDK initialized")
        }
    }

    override fun create(context: Context) {
        val sdkConfiguration: SdkConfiguration = SdkConfiguration.Builder(adUnitId)
                .withLogLevel(if (BuildConfig.DEBUG) MoPubLog.LogLevel.DEBUG else MoPubLog.LogLevel.NONE)
                .withLegitimateInterestAllowed(false)
                .build()

        MoPub.initializeSdk(context, sdkConfiguration, initSdkListener())
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf(TimberInitializer::class.java)
    }

    companion object {
        const val adUnitId = "###############"
    }
}