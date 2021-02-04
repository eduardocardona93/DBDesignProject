package com.adworkshop.utills

import android.app.Activity
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar


object CommonUtils {


    private lateinit var alertDialog: AlertDialog


    fun showToast(activity: Activity, message: String?) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    fun showLog(about: String, message: String) {
        Log.e(about, message)
    }

    fun showSnackbar(view: View?, message: String?) {
        val snackbar1 = Snackbar.make(view!!, message!!, Snackbar.LENGTH_SHORT)
        snackbar1.show()
    }

}
