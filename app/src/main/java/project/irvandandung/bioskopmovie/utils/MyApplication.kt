package project.irvandandung.bioskopmovie.utils

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import project.irvandandung.bioskopmovie.core.di.databaseModule
import project.irvandandung.bioskopmovie.core.di.networkModule
import project.irvandandung.bioskopmovie.core.di.repositoryModul
import project.irvandandung.bioskopmovie.di.useCaseModul
import project.irvandandung.bioskopmovie.di.viewModelModule

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModul,
                    useCaseModul,
                    viewModelModule
                )
            )
        }
    }
}