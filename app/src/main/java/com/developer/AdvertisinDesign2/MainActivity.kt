package com.developer.AdvertisinDesign2

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.developer.AdvertisinDesign2.databinding.ActivityMainBinding
import com.developper.investproject.`class`.Constants
import com.unity3d.ads.IUnityAdsListener
import com.unity3d.ads.UnityAds
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val testMode = false
    private val adUnitId = "Rewarded_Android"
    private val unityGameID = "4764582"
    private var isRewardedReady = false
    lateinit var preferences: SharedPreferences
    var summa: Float = 0.0F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferences = getSharedPreferences("baza", MODE_PRIVATE)

        saveUser(summa.toString())



        summa = getMoney()
        val df= DecimalFormat("#.###")
        binding.txtReward.text=df.format(getMoney())

        binding.historyLayout.setOnClickListener {
            startActivity(Intent(this,HistoryWithdrawalActivity::class.java))
            finish()
        }

        binding.layoutWallet.setOnClickListener {
            startActivity(Intent(this,WithdrawalActivity::class.java))
            finish()
        }

        binding.telegramCardView.setOnClickListener {
            val telegramIntent = Intent().apply {
                action = Intent.ACTION_VIEW
                data = Uri.parse("https://t.me/Future_Technology_Company")
            }
            startActivity(telegramIntent)
        }


        // Declare a new listener:
        val myAdsListener: IUnityAdsListener = object : IUnityAdsListener {
            override fun onUnityAdsReady(s: String?) {
                isRewardedReady = true
                Toast.makeText(this@MainActivity, "ads ready", Toast.LENGTH_SHORT).show()
            }

            override fun onUnityAdsStart(s: String?) {}
            override fun onUnityAdsFinish(result: String, finishState: UnityAds.FinishState) {

                // Implement conditional logic for each ad completion status:
                if (finishState.equals(UnityAds.FinishState.COMPLETED)) {
                    summa = getMoney()
                    addMoney()
                    val df=DecimalFormat("#.###")
                    binding.txtReward.text=df.format(getMoney())
                    // Reward the user for watching the ad to completion.
                } else if (result== UnityAds.FinishState.SKIPPED.toString()) {
                    // Do not reward the user for skipping the ad.
                    Toast.makeText(
                        this@MainActivity,
                        "Do not reward the user for skipping the ad",
                        Toast.LENGTH_SHORT
                    ).show()
                } else // Do not reward the user for skipping the ad.
                {
                    if (result == UnityAds.FinishState.ERROR.toString()) {
                        // Log an error.
                        Toast.makeText(
                            this@MainActivity,
                            "Проверьте интернет соединение ",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

            override fun onUnityAdsError(unityAdsError: UnityAds.UnityAdsError?, s: String?) {
                // Implement functionality for a Unity Ads service error occurring.
            }
        }
        // Add the listener to the SDK:
        UnityAds.addListener(myAdsListener)
        // Initialize the SDK:
        UnityAds.initialize(this, unityGameID, testMode)
    }

    // Implement a function to display an ad if the Ad Unit is ready:
    private fun DisplayRewardedVideoAd() {
        UnityAds.show(this, adUnitId)
    }

    fun showAd(view: View?) {
        DisplayRewardedVideoAd()
    }

    fun getMoney(): Float {
        return preferences.getFloat("ruble", 0F)
    }

    fun addMoney() {
        val temp = summa + 0.005F
        val myEdit = preferences.edit()
        myEdit.putFloat("ruble", temp)
        myEdit.apply()
    }

    fun saveUser(summa: String) {
        val myEdit: SharedPreferences.Editor = preferences.edit()
        myEdit.putString(Constants.sumaa, summa)


        myEdit.apply()
        //requireActivity().finish()
    }
}