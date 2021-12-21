package br.com.luishenrique.cartshop.ui.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import br.com.luishenrique.cartshop.R
import br.com.luishenrique.cartshop.ui.adapter.CartAdapter
import br.com.luishenrique.cartshop.ui.viewmodel.CartViewModel
import br.com.luishenrique.domain.entity.CarDTO
import br.com.luishenrique.domain.service.Result
import kotlinx.android.synthetic.main.fragment_list_cart.*
import org.koin.android.ext.android.inject

class ListCartFragment : Fragment(R.layout.fragment_list_cart) {

    private val viewModel by inject<CartViewModel>()
    private val adapter by lazy { CartAdapter(requireContext()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAllCarts()
        setRecyclerView()
        setObserver()

        adapter.onClick = {
            openCarDetail(it)
        }
        xTryAgain.setOnClickListener {
            viewModel.getAllCarts()
        }
    }

    private fun setObserver() {
        viewModel.car.observe(viewLifecycleOwner) {
            when(it) {
                is Result.Success -> {
                    xListCart.visibility = View.VISIBLE
                    xErrorContainer.visibility = View.GONE
                    loadCarList(it.data)
                }
                is Result.Error -> showError()
            }
        }

        viewModel.progressBar.observe(viewLifecycleOwner) {
            if (it) xProgressBar.visibility = View.VISIBLE
            else xProgressBar.visibility = View.GONE
        }
    }

    private fun showError() {
        xListCart.visibility = View.GONE
        xErrorContainer.visibility = View.VISIBLE
    }

    private fun loadCarList(cars: List<CarDTO>?) {
        adapter.cars = cars!!
    }

    private fun setRecyclerView() {
        xListCart.adapter = adapter
    }

    private fun openCarDetail(car: CarDTO) {

    }
}