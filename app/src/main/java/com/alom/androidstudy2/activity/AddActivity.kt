package com.alom.androidstudy2.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.alom.androidstudy2.Retrofit
import com.alom.androidstudy2.data.Request
import com.alom.androidstudy2.data.ResponseData
import com.alom.androidstudy2.databinding.ActivityAddBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnSave.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val price = binding.etPrice.text.toString()
            val time = binding.etTime.text.toString()

            if (title.isEmpty() || price.isEmpty() || time.isEmpty()) {
                Toast.makeText(this, "입력해라", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val requestData = Request(title, price, time)

            val returnIntent = Intent()
            returnIntent.putExtra("requestData", requestData)
            setResult(RESULT_OK, returnIntent)
            finish()
        }
    }
}