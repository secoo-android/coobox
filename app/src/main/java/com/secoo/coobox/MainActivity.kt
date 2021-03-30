package com.secoo.coobox

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.secoo.coobox.library.ktx.java.io.directorySize
import com.secoo.coobox.library.logger.LogAssistant
import com.secoo.coobox.library.logger.smartLogD
import java.io.File

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        LogAssistant.isLogEnabled = true

         
    }


}