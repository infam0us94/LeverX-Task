package com.project.leverxtask

import android.app.Application
import com.project.leverxtask.di.AppComponent
import com.project.leverxtask.di.AppModule
import com.project.leverxtask.di.DaggerAppComponent

class MyApp : Application() {
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    fun getAppComponent(): AppComponent {
        return appComponent
    }
}