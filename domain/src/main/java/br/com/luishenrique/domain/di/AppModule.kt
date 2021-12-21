package br.com.luishenrique.domain.di

import br.com.luishenrique.domain.repository.CartRepository
import br.com.luishenrique.domain.repository.CartRepositoryImpl
import br.com.luishenrique.domain.service.ApiCartService
import br.com.luishenrique.domain.usecase.CartUseCaseImpl
import br.com.luishenrique.domain.usecase.CartUserCase
import br.com.luishenrique.domain.utils.BASE_URL
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val service = module {
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<ApiCartService> {
        get<Retrofit>().create(ApiCartService::class.java)
    }
}

val repository = module {
    single<CartRepository> { CartRepositoryImpl(apiService = get()) }
}

val useCase = module {
    single<CartUserCase> { CartUseCaseImpl(repository = get()) }
}