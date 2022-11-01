package com.developer.AdvertisinDesign2

import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.annotation.RequiresApi
import com.developer.AdvertisinDesign2.databinding.ActivityWithdrawalBinding
import com.developper.investproject.`class`.Constants
import java.text.DecimalFormat

class WithdrawalActivity : AppCompatActivity() {
    lateinit var binding: ActivityWithdrawalBinding
    lateinit var preferences: SharedPreferences

    @RequiresApi(Build.VERSION_CODES.M)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWithdrawalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        preferences = this.getSharedPreferences("baza", MODE_PRIVATE)

        val summa: String = preferences.getString(Constants.sumaa, null).toString()

        binding.edBalance.setText(summa)


        binding.imageButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        val Summa: String = binding.edSumma.toString()
        val Number: String = binding.edNumber.toString()
        val Email: String = binding.edEmail.toString()
        if (Summa.isNullOrEmpty() || Number.isNullOrEmpty() || Email.isNullOrEmpty()) {
            binding.edSumma.setHintTextColor(getColor(R.color.red))
            binding.edEmail.setHintTextColor(getColor(R.color.red))
            binding.edNumber.setHintTextColor(getColor(R.color.red))
            binding.btnNext.isEnabled = false

        } else {
            binding.edSumma.setHintTextColor(getColor(R.color.black))
            binding.edEmail.setHintTextColor(getColor(R.color.black))
            binding.edNumber.setHintTextColor(getColor(R.color.black))
        }

        binding.edSumma.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            @RequiresApi(Build.VERSION_CODES.M)
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                var summa = 0
                if (!p0.isNullOrEmpty()) {
                    summa = p0.toString().toInt()
                }
                if (summa < 50) {
                    binding.edSumma.setTextColor(
                        getColor(R.color.red
                        )
                    )
                    binding.txtMin.visibility = View.VISIBLE

                    binding.txtMin.setTextColor(
                        getColor(R.color.red
                        )
                    )
                } else {
                    binding.edSumma.setTextColor(
                        getColor(R.color.black
                        )
                    )
                    binding.btnNext.isEnabled = true
                    binding.txtMin.visibility = View.GONE
                    binding.txtMin.setTextColor(
                        getColor(R.color.black
                        )
                    )

                }
                val Number: String = binding.edNumber.toString()
                val Email: String = binding.edEmail.toString()
                if (Number.isNullOrEmpty() || Email.isNullOrEmpty()) {
                    binding.edEmail.setHintTextColor(getColor(R.color.red))
                    binding.edNumber.setHintTextColor(getColor(R.color.red))
                    binding.btnNext.isEnabled = false

                } else {
                    binding.edEmail.setHintTextColor(getColor(R.color.black))
                    binding.edNumber.setHintTextColor(getColor(R.color.black))
                    binding.btnNext.isEnabled = true

                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
        binding.btnNext.setOnClickListener {
            startActivity(Intent(this, HistoryWithdrawalActivity::class.java))
            finish()
        }

    }
}