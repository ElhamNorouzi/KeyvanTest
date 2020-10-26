package com.app.keyvantest.utils

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.app.keyvantest.R
import com.google.android.material.snackbar.Snackbar


abstract class BaseActivity : AppCompatActivity() {

    protected fun showLongToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    protected fun showShortToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    protected fun showLoading(loading: View) {
        loading.visibility = View.VISIBLE
    }

    protected fun hideLoading(loading: View) {
        loading.visibility = View.GONE
    }

    protected fun getColorRes(colorRes: Int): Int {
        return ContextCompat.getColor(this, colorRes)
    }

    protected fun getDrawableRes(drawableRes: Int): Drawable? {
        return ContextCompat.getDrawable(this, drawableRes)
    }

    protected fun showViewWithAnimation(view: View) {
        view.visibility = View.VISIBLE
        view.alpha = 0.0f

        // Start the animation
        view.animate()
            .translationY(0f)
            .alpha(1.0f)
            .duration = 300
    }

    protected fun initLoading(loading: View?, show: Boolean) {
        loading!!.visibility = if (show) View.VISIBLE else View.GONE
    }


    protected fun showSnackBar(layout: View, msg: String, onclick: (() -> Unit)?) {
        val mSnackBar = Snackbar.make(layout, msg, Snackbar.LENGTH_LONG)
            .setAction("بستن") {
                onclick.let { click ->
                    click?.invoke()
                }
            }
            .setActionTextColor(getColorRes(R.color.hbRed))
            .show()
    }


}