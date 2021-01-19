package com.android.example.github.initializer

import android.content.Context
import androidx.startup.Initializer
import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.AppsFlyerLib
import timber.log.Timber

class AppsFlyerInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        val conversionDataListener = object : AppsFlyerConversionListener {
            override fun onConversionDataSuccess(data: MutableMap<String, Any>?) {
                data?.let { cvData ->
                    cvData.map {
                        Timber.i("Timber: conversion_attribute:  ${it.key} = ${it.value}")
                    }
                }
            }

            override fun onConversionDataFail(error: String?) {
                Timber.e("Timber: error onAttributionFailure :  $error")
            }

            override fun onAppOpenAttribution(data: MutableMap<String, String>?) {
                data?.map {
                    Timber.d("Timber: onAppOpen_attribute: ${it.key} = ${it.value}")
                }
            }

            override fun onAttributionFailure(error: String?) {
                Timber.e("Timber: error onAttributionFailure :  $error")
            }
        }

        AppsFlyerLib.getInstance().init(appsflyerDevKey, conversionDataListener, context)
        AppsFlyerLib.getInstance().start(context)
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf(TimberInitializer::class.java)
    }

    companion object {
        const val appsflyerDevKey = "###############"
    }
}