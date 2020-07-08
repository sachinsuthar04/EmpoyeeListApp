package com.example.di

import com.example.base.BaseActivity
import com.example.base.BaseViewModel
import com.example.base.MyApplication
import com.example.di.module.AppModule
import com.example.di.module.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, AppModule::class])
interface AppComponent {

    fun inject(myApplication: MyApplication)
    fun inject(baseActivity: BaseActivity)
    fun inject(baseViewModel: BaseViewModel)

    @Component.Builder
    interface Builder {
        fun build(): AppComponent
        fun appModule(appModule: AppModule): Builder
        fun networkModule(networkModule: NetworkModule): Builder
    }
}