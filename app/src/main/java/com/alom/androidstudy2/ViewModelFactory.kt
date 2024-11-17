package com.alom.androidstudy2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(com.alom.androidstudy2.ViewModel::class.java)) {
            return ViewModel(repository) as T
        }
        throw IllegalArgumentException("ViewModel class not found")
    }
}