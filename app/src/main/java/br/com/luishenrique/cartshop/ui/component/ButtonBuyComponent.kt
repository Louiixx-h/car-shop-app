package br.com.luishenrique.cartshop.ui.component

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentActivity
import br.com.luishenrique.cartshop.R
import br.com.luishenrique.domain.entity.CarDTO
import kotlinx.android.synthetic.main.component_progressbar_button.view.*
import kotlinx.coroutines.*

class ButtonBuyComponent: ConstraintLayout {

    @JvmOverloads
    constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
    ): super(context, attrs, defStyleAttr)

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ): super(context, attrs, defStyleAttr, defStyleRes)

    init {
        LayoutInflater.from(context).inflate(
            R.layout.component_progressbar_button,
            this,
            true
        )
    }

    fun buy(car: CarDTO, activity: FragmentActivity) {
        xButton.visibility = View.GONE
        MainScope().launch {
            xProgressBarButton.visibility = View.VISIBLE
            xAvi.visibility = View.VISIBLE
            withContext(Dispatchers.IO) {
                delay(800)
            }
            xProgressBarButton.visibility = View.GONE
            xAvi.visibility = View.GONE
            xButton.visibility = View.VISIBLE
            openDialog(car, activity)
        }
    }

    private fun openDialog(car: CarDTO, activity: FragmentActivity) {
        DialogBuyComponent(activity).show()
    }
}