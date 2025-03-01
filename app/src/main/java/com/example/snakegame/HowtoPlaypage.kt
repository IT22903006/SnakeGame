package com.example.snakegame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class HowtoPlaypage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_howto_playpage)

        //get the component with id - go to home
        val buttonPlay = findViewById<Button>(R.id.btnback2home)

        buttonPlay.setOnClickListener{
            val nextPage = Intent(this, HomePage::class.java);
            startActivity(nextPage);
            finish();
        }

    }
}