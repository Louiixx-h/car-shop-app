package br.com.luishenrique.domain.repository

import br.com.luishenrique.domain.entity.CartDTO
import br.com.luishenrique.domain.service.ApiCartService
import br.com.luishenrique.domain.service.Result

class CartRepositoryImpl(private val apiService: ApiCartService): CartRepository {

    override suspend fun getAllCart(page: Int): Result<List<CartDTO>?> {
        return try {
            val response = apiService.getAllCarts(page)
            if (response.isSuccessful) Result.Success(data = response.body())
            else Result.Error(exception = Exception("Lista de carros vazia!"))
        } catch (e: Exception) {
            Result.Error(exception = Exception("Erro ao buscar na api!"))
        }
    }
}