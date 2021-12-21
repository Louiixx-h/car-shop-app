package br.com.luishenrique.domain.usecase

import br.com.luishenrique.domain.entity.CarDTO
import br.com.luishenrique.domain.repository.CartRepository
import br.com.luishenrique.domain.service.Result

class CartUseCaseImpl(private val repository: CartRepository): CartUserCase {

    override suspend fun getAllCarts(page: Int): Result<List<CarDTO>?> {
        return repository.getAllCart(page)
    }
}