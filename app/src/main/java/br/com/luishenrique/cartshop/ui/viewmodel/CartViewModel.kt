package br.com.luishenrique.cartshop.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.luishenrique.domain.entity.CartDTO
import br.com.luishenrique.domain.service.Result
import br.com.luishenrique.domain.usecase.CartUserCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CartViewModel(private val useCase: CartUserCase) {

    private val _cart: MutableLiveData<Result<List<CartDTO>>> = MutableLiveData()
    val cart: LiveData<Result<List<CartDTO>>> = _cart

    private val _progressBar: MutableLiveData<Boolean> = MutableLiveData(false)
    val progressBar: LiveData<Boolean> = _progressBar

    private val pageCount: MutableLiveData<Int> = MutableLiveData(1)

    fun getAllCarts() {
        CoroutineScope(Dispatchers.IO).launch {
            _progressBar.postValue(true)
            withContext(Dispatchers.IO) {
                pageCount.value?.let {
                    useCase.getAllCarts(it)
                }
            }
            _progressBar.postValue(false)
        }
    }

    fun nextPage() {
        pageCount.value?.plus(1)
        getAllCarts()
    }
}
