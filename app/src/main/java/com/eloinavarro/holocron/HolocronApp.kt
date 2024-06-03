package com.eloinavarro.holocron

import android.app.Application
import com.eloinavarro.holocron.Provider.Companion.data
import com.eloinavarro.holocron.Provider.Companion.domain
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class HolocronApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@HolocronApp)
            modules(data, domain)
        }
    }
}