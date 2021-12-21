package br.com.luishenrique.domain.usecase

import br.com.luishenrique.domain.entity.CartDTO
import br.com.luishenrique.domain.repository.CartRepository
import br.com.luishenrique.domain.service.Result
import kotlinx.coroutines.withContext

class CartUseCaseImpl(private val repository: CartRepository): CartUserCase {

    override suspend fun getAllCarts(page: Int): Result<List<CartDTO>?> {
        return repository.getAllCart(page)
    }
}