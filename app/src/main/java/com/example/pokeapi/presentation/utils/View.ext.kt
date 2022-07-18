package com.example.pokeapi.presentation.utils

import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide

fun ImageView.loadImage(url: String) {
    Glide.with(context)
        .load(url)
        .fallback(android.R.drawable.stat_notify_error)
        .error(android.R.drawable.stat_notify_error)
        .into(this)
}

fun Fragment.showToast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
}

fun TextView.setTextOrDefault(@StringRes id: Int, text: String, default: String = "n/a") {
    this.text = context.getString(id).format(text.ifBlank { default })
}