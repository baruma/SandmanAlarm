package com.example.sandmanalarm

import android.app.Application
import android.content.Context
import com.example.sandmanalarm.alarmList.AlarmListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module

class SandmanApp : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: SandmanApp? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger()
            androidContext(this@SandmanApp)
            modules(mainModule)
        }
    }

    val mainModule = module {
        viewModel {AlarmListViewModel()}
    }
}
