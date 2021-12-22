package br.com.luishenrique.cartshop.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.luishenrique.domain.entity.CarDTO
import br.com.luishenrique.domain.service.Result
import br.com.luishenrique.domain.usecase.CartUserCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CartViewModel(private val useCase: CartUserCase) {

    private val _car: MutableLiveData<Result<List<CarDTO>?>?> = MutableLiveData()
    val car: LiveData<Result<List<CarDTO>?>?> = _car

    private val _progressBar: MutableLiveData<Boolean> = MutableLiveData(false)
    val progressBar: LiveData<Boolean> = _progressBar

    private val pageCount: MutableLiveData<Int> = MutableLiveData(1)

    fun getAllCarts() {
        CoroutineScope(Dispatchers.IO).launch {
            _progressBar.postValue(true)
            val response = withContext(Dispatchers.IO) {
                pageCount.value?.let {
                    useCase.getAllCarts(it)
                }
            }
            _car.postValue(response)
            _progressBar.postValue(false)
        }
    }

    fun nextPage() {
        pageCount.postValue(pageCount.value?.inc())
        getAllCarts()
    }

    fun previousPage() {
        if (pageCount.value!! > 1) {
            pageCount.postValue(pageCount.value?.dec())
            getAllCarts()
        }
    }

    fun fixImageList(cars: List<CarDTO>): List<CarDTO> {
        return useCase.fixImageList(cars)
    }
}
