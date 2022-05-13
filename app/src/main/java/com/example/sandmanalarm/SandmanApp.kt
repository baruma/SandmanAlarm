package com.example.sandmanalarm

import android.app.Application
import com.example.sandmanalarm.alarm.AlarmListViewModel
import com.example.sandmanalarm.data.data_alarm.AlarmDatabase
import com.example.sandmanalarm.data.data_alarm.AlarmRepository
import kotlinx.coroutines.Dispatchers
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
        single { AlarmRepository(get()) }
    }
}
