package com.developer.AdvertisinDesign2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.developer.AdvertisinDesign2.databinding.ActivityHistoryWithdrawalBinding

class HistoryWithdrawalActivity : AppCompatActivity() {
    lateinit var binding: ActivityHistoryWithdrawalBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityHistoryWithdrawalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imageButton.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
    }
}