package br.com.luishenrique.domain.usecase

import br.com.luishenrique.domain.entity.CarDTO
import br.com.luishenrique.domain.service.Result

interface CartUserCase {
    suspend fun getAllCarts(page: Int): Result<List<CarDTO>?>
    fun fixImageList(cars: List<CarDTO>): List<CarDTO>
}