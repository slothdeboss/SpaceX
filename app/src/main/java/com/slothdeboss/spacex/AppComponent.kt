package com.slothdeboss.spacex

import android.app.Application
import com.slothdeboss.spacex.di.networkModule
import com.slothdeboss.spacex.di.persistenceModule
import com.slothdeboss.spacex.di.repositoryModule
import com.slothdeboss.spacex.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppComponent: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppComponent)
            modules(
                viewModelModule,
                networkModule,
                repositoryModule,
                persistenceModule
            )
        }
    }

}