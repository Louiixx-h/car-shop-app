package br.com.luishenrique.cartshop

import android.app.Application
import br.com.luishenrique.cartshop.di.viewModel
import br.com.luishenrique.domain.di.repository
import br.com.luishenrique.domain.di.service
import br.com.luishenrique.domain.di.useCase
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    service,
                    repository,
                    useCase,
                    viewModel
                )
            )
        }
    }
}