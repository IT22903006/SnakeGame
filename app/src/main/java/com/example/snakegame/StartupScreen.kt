package com.example.snakegame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class StartupScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_startup_screen)

        //get the component with id - go to home page
        val buttonPlay = findViewById<Button>(R.id.btnStarted)

        buttonPlay.setOnClickListener{
            val nextPage = Intent(this, HomePage::class.java);
            startActivity(nextPage);
            finish();
        }
    }
}