package com.example.base

import android.app.Application
import androidx.databinding.ObservableBoolean
import com.example.di.AppComponent
import com.example.di.DaggerAppComponent
import com.example.di.module.AppModule
import com.example.di.module.NetworkModule
import com.example.util.AppPreferences
import com.facebook.drawee.backends.pipeline.Fresco
import javax.inject.Inject

class MyApplication : Application() {

    @Inject
    lateinit var appPreferences: AppPreferences

    companion object {
        lateinit var application: MyApplication
        lateinit var appComponent: AppComponent
        var isInternetAvailable: ObservableBoolean = ObservableBoolean(false)
    }

    override fun onCreate() {
        super.onCreate()
        application = this
        Fresco.initialize(this)
        appComponent = DaggerAppComponent.builder()
            .networkModule(NetworkModule)
            .appModule(
                AppModule(
                    application
                )
            ).build()

        appComponent.inject(this)

    }
}