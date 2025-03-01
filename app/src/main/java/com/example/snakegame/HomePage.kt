package com.example.snakegame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class HomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        //get the component with id - go to PLAY
        val buttonPlay = findViewById<Button>(R.id.btnPlay)

        buttonPlay.setOnClickListener{
            val nextPage = Intent(this, MainActivity::class.java);
            startActivity(nextPage);
            finish();
        }

        //get the component with id - go to how to play
        val buttonHow2Play = findViewById<Button>(R.id.btnHow2Play)

        buttonHow2Play.setOnClickListener{
            val nextPage = Intent(this, HowtoPlaypage::class.java);
            startActivity(nextPage);
            finish();
        }

        //get the component with id - go to Controls
        val buttonControl = findViewById<Button>(R.id.btnControls)

        buttonControl.setOnClickListener{
            val nextPage = Intent(this, ContorlPlage::class.java);
            startActivity(nextPage);
            finish();
        }

        //get the component with id - leave game
        val buttonLeave = findViewById<Button>(R.id.btnLeave)

        buttonLeave.setOnClickListener{
            val nextPage = Intent(this, StartupScreen::class.java);
            startActivity(nextPage);
            finish();
        }

    }
}