package br.com.luishenrique.cartshop.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import br.com.luishenrique.cartshop.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_sort_24)

        with(supportFragmentManager.beginTransaction()) {
            replace(R.id.xFragmentContainer, ListCartFragment(), LIST_CAR_FRAGMENT)
            commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.xMenuMake -> { getChildFragment()?.sortByMake?.invoke() }
            R.id.xMenuYear -> { getChildFragment()?.sortByYear?.invoke() }
            R.id.xMenuLowestPrice -> { getChildFragment()?.sortByLowestPrice?.invoke() }
            R.id.xMenuBiggestPrice -> { getChildFragment()?.sortByBiggestPrice?.invoke() }
        }
        return true
    }

    private fun getChildFragment(): ListCartFragment? {
        val fragment: Fragment? = supportFragmentManager.findFragmentByTag(LIST_CAR_FRAGMENT)
        if(fragment != null){
            if(fragment is ListCartFragment) {
                return fragment
            }
        }
        return null
    }

    override fun onResume() {
        super.onResume()
    }
}