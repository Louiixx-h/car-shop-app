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

const val LIST_CAR_FRAGMENT = "LIST_CAR_FRAGMENT"

class ListCartFragment : Fragment(R.layout.fragment_list_cart) {

    private val viewModel by inject<CartViewModel>()
    private val adapter by lazy { CartAdapter(requireContext()) }

    val sortByMake = {
        if (xListCart.visibility == View.VISIBLE) {
            adapter.sortListByMake()
        }
    }
    val sortByYear = {
        if (xListCart.visibility == View.VISIBLE) {
            adapter.sortListByYear()
        }
    }
    val sortByBiggestPrice = {
        if (xListCart.visibility == View.VISIBLE) {
            adapter.sortListByBiggestPrice()
        }
    }
    val sortByLowestPrice = {
        if (xListCart.visibility == View.VISIBLE) {
            adapter.sortListByLowestPrice()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAllCarts()
        setRecyclerView()
        setObserver()

        adapter.onClick = {
            openCarDetail(it)
        }
        adapter.onClickLoadMoreCarts = {
            viewModel.nextPage()
        }
        adapter.onClickDecrementPage = {
            viewModel.previousPage()
        }
        xDecPageEnd.setOnClickListener {
            viewModel.previousPage()
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
                    xEndContainer.visibility = View.GONE
                    xDecPageEnd.visibility = View.GONE
                    loadCarList(it.data!!)
                }
                is Result.Error -> showError()
                is Result.EmptyList -> showEndPage()
            }
        }

        viewModel.progressBar.observe(viewLifecycleOwner) {
            if (it) xProgressBar.visibility = View.VISIBLE
            else xProgressBar.visibility = View.GONE
        }
    }

    private fun showEndPage() {
        xListCart.visibility = View.GONE
        xEndContainer.visibility = View.VISIBLE
        xDecPageEnd.visibility = View.VISIBLE
    }

    private fun showError() {
        xListCart.visibility = View.GONE
        xErrorContainer.visibility = View.VISIBLE
    }

    private fun loadCarList(cars: List<CarDTO>) {
        adapter.cars = viewModel.fixImageList(cars)
    }

    private fun setRecyclerView() {
        xListCart.adapter = adapter
    }

    private fun openCarDetail(car: CarDTO) {
        val carBundle = Bundle()
        carBundle.putSerializable("car", car)

        with(parentFragmentManager.beginTransaction()) {
            add(
                R.id.xFragmentContainer,
                CarDetailFragment::class.java,
                carBundle,
                DETAIL_CAR_FRAGMENT
            )
            addToBackStack(LIST_CAR_FRAGMENT)
            commit()
        }
    }
}