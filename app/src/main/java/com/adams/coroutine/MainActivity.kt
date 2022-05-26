package com.adams.coroutine

import android.app.ProgressDialog.show
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.*
import java.lang.reflect.Array.get

class MainActivity : AppCompatActivity() {
    private var count = 0
    private lateinit var messageTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.tvCount)
        messageTextView = findViewById(R.id.tvMessage)
        val countButton = findViewById<Button>(R.id.btnCount)
        val downloadButton = findViewById<Button>(R.id.btnDownload)


        countButton.setOnClickListener{
            textView.text = count++.toString()
        }
        downloadButton.setOnClickListener {
            //coroutine
            CoroutineScope(Dispatchers.IO).launch {
                downloadUserData()

            }
        }

    }

    private suspend fun downloadUserData() {
        for (i in 1..200000){
            Log.i("My Tag", "Downloading user 4i in ${Thread.currentThread().name}")
            withContext(Dispatchers.Main){
                messageTextView.text = "Downloading user $i"

            }
            delay(100)
        }
    }
}

