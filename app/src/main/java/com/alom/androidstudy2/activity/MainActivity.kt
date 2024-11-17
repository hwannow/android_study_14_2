package com.alom.androidstudy2.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.recyclerview.widget.LinearLayoutManager
import com.alom.androidstudy2.Adapter
import com.alom.androidstudy2.RepositoryImpl
import com.alom.androidstudy2.ViewModel
import com.alom.androidstudy2.ViewModelFactory
import com.alom.androidstudy2.data.Request
import com.alom.androidstudy2.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ViewModel
    private lateinit var adapter: Adapter

    private val addActivityLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val requestData = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                result.data?.getParcelableExtra("requestData", Request::class.java)
            } else {
                result.data?.getParcelableExtra("requestData") as? Request
            }

            Log.d("MainActivity", requestData.toString())
            viewModel.addItem(requestData!!, onSuccess = { onSuccess() }, onFailure = { onFailure() })
        }
    }

    private fun onSuccess() {
        Toast.makeText(this, "업로드 성공", Toast.LENGTH_SHORT).show()
        viewModel.updateItems()
    }

    private fun onFailure() {
        Toast.makeText(this, "업로드 실패", Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = Intent(this, AddActivity::class.java)

        val repository = RepositoryImpl()
        val factory = ViewModelFactory(repository)

        viewModel = ViewModelProvider(this, factory).get(ViewModel::class.java)
        adapter = Adapter()

        binding.rcv.layoutManager = LinearLayoutManager(this)
        binding.rcv.adapter = adapter
        lifecycleScope.launch {
            viewModel.item.collect { items ->
                adapter.submitList(items)
                Log.d("MainActivity", items.toString())
            }
        }

        binding.btnAdd.setOnClickListener {
            addActivityLauncher.launch(intent)
        }
    }
}