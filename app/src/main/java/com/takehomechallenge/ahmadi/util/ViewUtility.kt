package com.takehomechallenge.ahmadi.util

import android.content.Context
import android.view.View
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.google.android.material.snackbar.Snackbar

fun shortToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun longToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}

fun shortSnackBar(context: Context, rootView: View, message: String) {
    Snackbar.make(context, rootView, message, Snackbar.LENGTH_SHORT).show()
}

fun longSnackBar(context: Context, rootView: View, message: String) {
    Snackbar.make(context, rootView, message, Snackbar.LENGTH_LONG).show()
}

fun showAlert(
    context: Context,
    title: String = "Alert",
    msg: String,
    positiveButton: String = "Ok",
    onConfirm: (MaterialDialog) -> Unit = { it.dismiss() }
) {
    MaterialDialog(context).show {
        title(text = title)
        message(text = msg)
        positiveButton(text = positiveButton) {
            onConfirm(it)
        }
    }
}

fun showChooseAlert(
    context: Context,
    title: String = "Alert",
    msg: String,
    positiveButton: String = "Ok",
    negativeButton: String = "No",
    onConfirm: (MaterialDialog) -> Unit = { it.dismiss() },
    onDecline: (MaterialDialog) -> Unit = { it.dismiss() },
) {
    MaterialDialog(context).show {
        title(text = title)
        message(text = msg)
        negativeButton(text = negativeButton){
            onDecline(it)
        }
        positiveButton(text = positiveButton) {
            onConfirm(it)
        }
    }
}