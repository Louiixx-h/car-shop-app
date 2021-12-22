package br.com.luishenrique.cartshop.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.luishenrique.cartshop.R
import br.com.luishenrique.cartshop.utils.render
import br.com.luishenrique.domain.entity.CarDTO
import kotlinx.android.synthetic.main.item_button.view.*
import kotlinx.android.synthetic.main.item_cart.view.*

class CartAdapter(
    private val context: Context
): RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    var cars: List<CarDTO> = emptyList()
    set(value) {
        val list = value as MutableList
        list.add(CarDTO())
        field = list
        notifyDataSetChanged()
    }

    var onClick: (CarDTO) -> Unit = {}
    var onClickLoadMoreCarts: () -> Unit = {}
    var onClickDecrementPage: () -> Unit = {}
    private val carType = 0
    private val buttonType = 1

    fun sortListByMake() {
        val list = cars as MutableList<CarDTO>
        list.removeLast()
        list.sortWith { o1: CarDTO, o2: CarDTO ->
            o1.make.compareTo(o2.make)
        }
        cars = list
    }

    fun sortListByYear() {
        val list = cars as MutableList<CarDTO>
        list.removeLast()
        list.sortBy {
            it.yearModel
        }
        cars = list
    }

    fun sortListByBiggestPrice() {
        val list = cars as MutableList<CarDTO>
        list.removeLast()
        list.sortWith (compareByDescending { it.price })
        cars = list
    }

    fun sortListByLowestPrice() {
        val list = cars as MutableList<CarDTO>
        list.removeLast()
        list.sortBy { it.price }
        cars = list
    }

    inner class ViewHolder(viewParam: View, viewType: Int): RecyclerView.ViewHolder(viewParam) {

        val view = viewParam
        val type = viewType
        private val image = viewParam.xCarImage
        private val make = viewParam.xMake
        private val model = viewParam.xModel
        private val km = viewParam.xKm
        private val price = viewParam.xPrice
        private val version = viewParam.xVersion
        private val year = viewParam.xYear
        private val favorite = viewParam.xFavorite
        private val isFavorite = false
        private val item = viewParam.xCartItem

        fun bind(car: CarDTO) {
            image.render(car.image, context)
            make.text = car.make
            model.text = run {" ${car.model}"}
            km.text = run {"${car.kM} km"}
            price.text = run { "R$ ${car.price}" }
            version.text = car.version
            year.text = car.yearModel.toString()

            favorite.setOnClickListener {
                isFavorite != isFavorite
                if (isFavorite) {
                    favorite.setImageDrawable(null)
                    favorite.setImageResource(R.drawable.ic_baseline_favorite_24)
                } else {
                    favorite.setImageDrawable(null)
                    favorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                }
            }

            item.setOnClickListener {
                onClick.invoke(car)
            }
        }
    }

    override fun getItemViewType(position: Int) = if (cars[position] == cars.last()) {
        buttonType
    } else carType

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = if (viewType == carType) LayoutInflater.from(parent.context).inflate(
            R.layout.item_cart,
            parent,
            false
        ) else LayoutInflater.from(parent.context).inflate(
            R.layout.item_button,
            parent,
            false
        )
        return ViewHolder(view, viewType)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder.type == carType) holder.bind(cars[position])
        else {
            holder.view.xLoadMoreCars.setOnClickListener {
                onClickLoadMoreCarts.invoke()
            }
            holder.view.xDecPage.setOnClickListener {
                onClickDecrementPage.invoke()
            }
        }
    }

    override fun getItemCount() = cars.size
}
