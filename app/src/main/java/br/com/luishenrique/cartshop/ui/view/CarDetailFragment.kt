package br.com.luishenrique.cartshop.ui.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import br.com.luishenrique.cartshop.R
import br.com.luishenrique.cartshop.utils.render
import br.com.luishenrique.domain.entity.CarDTO
import kotlinx.android.synthetic.main.fragment_car_detail.*
import kotlinx.android.synthetic.main.toolbar_back.*

const val DETAIL_CAR_FRAGMENT = "DETAIL_CAR_FRAGMENT"

class CarDetailFragment : Fragment(R.layout.fragment_car_detail) {

    private var car: CarDTO? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        car = arguments?.getSerializable("car") as CarDTO?

        initView(car)
    }

    private fun initView(car: CarDTO?) {
        if (car == null) return

        (activity as AppCompatActivity).supportActionBar?.hide()
        xToolbarTitle.text = run { "${car.make} ${car.model}" }
        xBack.setOnClickListener {
            (activity as AppCompatActivity).supportFragmentManager.popBackStack();
        }

        xViewBuy.setOnClickListener {
            xBuy.buy(car, requireActivity())
        }

        xCarImage.render(car.image, requireContext())
        xMake.text = car.make
        xModel.text = run { " ${car.model}" }
        xVersion.text = car.version
        xPrice.text = run { "R$ ${car.price}" }
        xMakeInfo.text = car.make
        xModelInfo.text = car.model
        xColorInfo.text = car.color
        xVersionInfo.text = car.version
        xKmInfo.text = run { "${car.kM} km"}
        xYearModelInfo.text = car.yearModel.toString()
        xYearFabInfo.text = car.yearFab.toString()
    }

    override fun onDetach() {
        (activity as AppCompatActivity).supportActionBar?.show()
        super.onDetach()
        onDestroy()
        Log.d("luis", "onDetach: destroy")
    }
}