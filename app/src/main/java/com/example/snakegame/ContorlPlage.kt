package com.example.snakegame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ContorlPlage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contorl_plage)

        //get the component with id - go to home
        val buttonPlay = findViewById<Button>(R.id.btnBack2hh)

        buttonPlay.setOnClickListener{
            val nextPage = Intent(this, HomePage::class.java);
            startActivity(nextPage);
            finish();
        }

    }
}