package com.example.sandmanalarm

import android.app.Application
import android.content.Context
import com.example.sandmanalarm.alarmList.AlarmListViewModel
import com.example.sandmanalarm.data.AlarmDatabase
import com.example.sandmanalarm.data.AlarmRepository
import com.example.sandmanalarm.data.domainModels.Alarm
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module

class SandmanApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@SandmanApp)
            modules(mainModule)
        }
    }

    val mainModule = module {
        viewModel { AlarmListViewModel(get(), Dispatchers.IO) }
        single { AlarmDatabase.getDatabase(this@SandmanApp) }
    }
}
