package com.example.hatzker

import android.annotation.SuppressLint
import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.*

class MainActivity : AppCompatActivity() {
    private lateinit var mButton: Button
    private lateinit var mProgressBar: ProgressBar
    private lateinit var mTextView: TextView
    private lateinit var mButtonClear: Button
    private lateinit var mEditText: EditText
    private var progressStatus = 0
    private var handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(ContentValues.TAG, "onCreate()")
        mTextView = findViewById(R.id.text)
        mProgressBar = findViewById(R.id.progressBar)
        mEditText = findViewById(R.id.EditText)
        mButton = findViewById(R.id.button)
        mButton.setOnClickListener {
            Log.d(ContentValues.TAG, "Button on")
            if (mEditText.text.isEmpty()) {
                Toast.makeText(this, R.string.button1, Toast.LENGTH_SHORT).show()
            }
            else {
                if (mEditText.text.contains("http://") || mEditText.text.contains("https://") ||
                    mEditText.text.contains("Http://") || mEditText.text.contains("Https://"))
                {Thread(Runnable {
                    val rnds1 = (1..95).random()
                    val rnds2 = (1..95).random()
                    val rnds3 = (1..95).random()
                    val rnds4 = (1..95).random()

                    while (progressStatus <= 98){
                        progressStatus +=1

                        if (progressStatus==rnds1 || progressStatus== rnds2 || progressStatus==rnds3 || progressStatus==rnds4){
                            val rnds = (500..3500).random()
                            Thread.sleep(rnds.toLong())
                        }
                        else { Thread.sleep(70)
                        }

                        handler.post {
                            mProgressBar.progress = progressStatus
                            mTextView.text = "$progressStatus %"
                            if (progressStatus == 99){
                                Thread.sleep(3000)
                                Toast.makeText(this, R.string.button2, Toast.LENGTH_LONG).show()
                                mTextView.text = "98 %"
                            }
                        }
                    }
                    }).start()
                    progressStatus = 0
                    mProgressBar.isIndeterminate = false
                }
                else{
                    Toast.makeText(this, R.string.button3, Toast.LENGTH_SHORT).show()
                }
            }
        }
        mButtonClear = findViewById(R.id.clear_text)
        mButtonClear.setOnClickListener {
            mTextView.text = ""
            mEditText.setText("")
            progressStatus = 0
            mProgressBar.progress = progressStatus
        }
    }
}