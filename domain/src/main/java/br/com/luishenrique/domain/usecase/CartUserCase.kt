package br.com.luishenrique.domain.usecase

import br.com.luishenrique.domain.entity.CartDTO
import br.com.luishenrique.domain.service.Result

interface CartUserCase {
    suspend fun getAllCarts(page: Int): Result<List<CartDTO>?>
}