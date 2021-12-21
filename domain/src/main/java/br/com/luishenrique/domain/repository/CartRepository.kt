package br.com.luishenrique.domain.repository

import br.com.luishenrique.domain.entity.CartDTO
import br.com.luishenrique.domain.service.Result

interface CartRepository {

    suspend fun getAllCart(page: Int): Result<List<CartDTO>?>
}