package com.alom.androidstudy2

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alom.androidstudy2.activity.MainActivity
import com.alom.androidstudy2.data.Item
import com.alom.androidstudy2.data.Request
import com.alom.androidstudy2.data.ResponseData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModel(private val repository:Repository) : ViewModel() {
    private val _item = MutableStateFlow<List<Item>>(emptyList())
    val item: StateFlow<List<Item>> get() = _item

    init {
        updateItems()
    }

    fun updateItems() {
        viewModelScope.launch {
            repository.getItem().collect { items ->
                _item.value = items
            }
        }
    }

    fun addItem(request: Request, onSuccess: () -> Unit, onFailure: () -> Unit) {
        viewModelScope.launch {
            val result = repository.addItem(request)
            if (result.isSuccess) {
                onSuccess()
            } else {
                onFailure()
            }
        }
    }

}