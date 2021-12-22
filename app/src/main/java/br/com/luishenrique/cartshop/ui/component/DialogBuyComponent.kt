package br.com.luishenrique.cartshop.ui.component

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.view.Window
import br.com.luishenrique.cartshop.R
import kotlinx.android.synthetic.main.component_buy_dialog.*

class DialogBuyComponent(activity: Activity): Dialog(activity) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.component_buy_dialog);

        xYesDialog.setOnClickListener {
            dismiss()
        }
        xNoDialog.setOnClickListener {
            dismiss()
        }
    }
}