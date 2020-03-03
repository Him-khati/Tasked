package com.himanshu.tasked.core.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Generic view model provider.
 *
 * @param key The key to use to identify the ViewModel.
 * @param factory Function creates a new instance of the ViewModel.
 * @return A ViewModel that is an instance of the given type [VM].
 * @see ViewModel
 */
@Suppress("UNCHECKED_CAST")
fun <VM : ViewModel> AppCompatActivity.viewModel(
    key: String? = null,
    factory: () -> VM
): VM {
    val factoryViewModel = factory()
    val viewModelProviderFactory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return factoryViewModel as T
        }
    }

    return if (key != null) {
        ViewModelProvider(this, viewModelProviderFactory).get(key, factoryViewModel::class.java)
    } else {
        ViewModelProvider(this, viewModelProviderFactory).get(factoryViewModel::class.java)
    }
}