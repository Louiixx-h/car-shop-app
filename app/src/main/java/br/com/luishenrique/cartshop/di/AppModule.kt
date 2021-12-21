package br.com.luishenrique.cartshop.di

import br.com.luishenrique.cartshop.ui.viewmodel.CartViewModel
import org.koin.dsl.module

val viewModel = module {
    single {
        CartViewModel(get())
    }
}