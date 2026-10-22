package com.ultron.boss

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import java.util.Locale
import kotlin.random.Random

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private lateinit var tts: TextToSpeech
    private var isBangla = true
    private var isAutoTradeOn = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tts = TextToSpeech(this, this)
        
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECORD_AUDIO, Manifest.permission.POST_NOTIFICATIONS), 101)
        }

        val serviceIntent = Intent(this, UltronService::class.java)
        startForegroundService(serviceIntent)

        findViewById<Button>(R.id.btnAutoTrade)?.setOnClickListener {
            isAutoTradeOn = !isAutoTradeOn
            if(isAutoTradeOn) {
                speakBoss("BOSS Auto trade on kore dilam. Best time khujtesi.")
                startTradingBrains()
            } else {
                speakBoss("Auto trade off kore dilam BOSS.")
            }
        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            tts.language = Locale("bn", "BD")
            speakBoss("Ami Tomar BOSS. ULTRON SUPER MODE V1 active. 65 brain plus 100 trading brain ready. Screen off thakleo ami sunbo BOSS.")
        }
    }

    private fun speakBoss(text: String) {
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }

    private fun startTradingBrains() {
        Thread {
            while(isAutoTradeOn) {
                val humanDelay = Random.nextLong(2000, 7000)
                Thread.sleep(humanDelay)
                val conf = Random.nextInt(70, 98)
                Thread.sleep(10000)
            }
        }.start()
    }

    fun onVoiceCommand(command: String) {
        when {
            command.contains("English shikhao", true) -> {
                isBangla = false
                tts.language = Locale.ENGLISH
                speakBoss("Okay BOSS, Now English. 165 brains active.")
            }
            command.contains("ke tumi", true) -> {
                speakBoss("Ami Tomar BOSS. Apnar personal ULTRON.")
            }
        }
    }

    override fun onDestroy() {
        tts.stop()
        tts.shutdown()
        super.onDestroy()
    }
}
