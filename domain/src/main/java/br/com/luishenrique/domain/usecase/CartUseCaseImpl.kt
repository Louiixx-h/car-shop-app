package br.com.luishenrique.domain.usecase

import br.com.luishenrique.domain.entity.CarDTO
import br.com.luishenrique.domain.repository.CartRepository
import br.com.luishenrique.domain.service.Result
import br.com.luishenrique.domain.utils.addCharAtIndex

class CartUseCaseImpl(private val repository: CartRepository): CartUserCase {

    override suspend fun getAllCarts(page: Int): Result<List<CarDTO>?> {
        return repository.getAllCart(page)
    }

    override fun fixImageList(cars: List<CarDTO>): List<CarDTO> {
        val newCars: MutableList<CarDTO> = mutableListOf()
        cars.forEach {
            it.image = it.image.addCharAtIndex('s', 4)
            newCars.add(it)
        }
        return newCars
    }
}