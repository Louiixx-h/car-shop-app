package br.com.luishenrique.domain.service

import br.com.luishenrique.domain.entity.CarDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiCartService {

    @GET("OnlineChallenge/Vehicles")
    suspend fun getAllCarts(@Query("page") page: Int): Response<List<CarDTO>>
}