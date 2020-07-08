package com.example.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.util.AppPreferences
import com.example.util.AppUtils
import com.example.util.TagValue
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {

    @Provides
    @Singleton
    internal fun providerApplication(): Application {
        return application
    }

    @Provides
    @Singleton
    internal fun providerContext(): Context {
        return application
    }

    @Provides
    @Singleton
    internal fun providerPref(): SharedPreferences {
        return application.applicationContext.getSharedPreferences(TagValue.PREFRENCE, 0)
    }


    @Provides
    @Singleton
    internal fun providerPrefEditor(): SharedPreferences.Editor {
        return application.applicationContext.getSharedPreferences(TagValue.PREFRENCE, 0).edit()
    }

    @Provides
    @Reusable
    internal fun providerUtils(): AppUtils {
        return AppUtils.newInstance(application)
    }

    @Provides
    @Reusable
    internal fun providerPreferences(): AppPreferences {
        return AppPreferences.newInstance(application)
    }
}