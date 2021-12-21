package br.com.luishenrique.cartshop.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import br.com.luishenrique.cartshop.R
import br.com.luishenrique.cartshop.utils.addCharAtIndex
import br.com.luishenrique.cartshop.utils.convertToBRL
import br.com.luishenrique.domain.entity.CarDTO
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.item_cart.view.*

class CartAdapter(
    private val context: Context
): RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    var cars: List<CarDTO> = emptyList()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    var onClick: (CarDTO) -> Unit = {}

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val image = view.xCarImage
        private val make = view.xMake
        private val model = view.xModel
        private val km = view.xKm
        private val price = view.xPrice
        private val version = view.xVersion
        private val year = view.xYear
        private val favorite = view.xFavorite
        private val isFavorite = false
        private val item = view.xCartItem

        fun bind(car: CarDTO) {
            image.render(car.image.addCharAtIndex('s', 4), context)
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

        private fun ImageView.render(uri: String, context: Context) {
            Glide.with(context)
                .load(uri)
                .placeholder(R.drawable.ic_loading_image)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .into(this)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_cart,
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(cars[position])
    }

    override fun getItemCount() = cars.size

}
