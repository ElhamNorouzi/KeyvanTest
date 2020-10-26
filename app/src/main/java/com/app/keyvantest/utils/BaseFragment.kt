package com.app.keyvantest.utils

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.app.keyvantest.R
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment : Fragment() {

    protected fun showLongToast(message: String?) {
        if (message != null) {
            Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
        }
    }

    protected fun showShortToast(message: String?) {
        if (message != null) {
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }
    }


    protected fun getColorRes(colorRes: Int): Int {
        return if (activity != null) ContextCompat.getColor(requireActivity(), colorRes) else 0
    }

    protected fun getDrawableRes(drawableRes: Int): Drawable? {
        return ContextCompat.getDrawable(requireActivity(), drawableRes)
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