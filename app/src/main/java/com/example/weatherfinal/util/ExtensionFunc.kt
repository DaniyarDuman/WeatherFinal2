package com.example.weatherfinal.util

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

fun EditText.textChanges(): Flow<String> = callbackFlow {
    val listener = object : TextWatcher {
        override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {
            charSequence?.let { trySend(it.toString()) }
        }

        override fun afterTextChanged(editable: Editable?) {}
    }

    this@textChanges.addTextChangedListener(listener)

    awaitClose { this@textChanges.removeTextChangedListener(listener) }
}
