package com.example.voteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.voteapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)
        val sp = getSharedPreferences("passwordinfo", MODE_PRIVATE)
        val spb = getSharedPreferences("boothid", MODE_PRIVATE)
        b.boothinfo.text = when (spb.getInt("booth", 0)) {
            1 -> "Booth 1"
            2 -> "Booth 2"
            3 -> "Booth 3"
            4 -> "Booth 4"
            else -> ""
        }
        b.voterButton.setOnClickListener {


            if (sp.getBoolean("isPasswordSet", false) == false) {
                Toast.makeText(this, "Voting is not started yet", Toast.LENGTH_LONG).show()

            } else {
                val loginIntent = Intent(this@MainActivity, Voterpassword::class.java)
                startActivity(loginIntent)
            }
        }
        b.adminButton.setOnClickListener {
            val loginIntent = Intent(this@MainActivity, Adminpassword::class.java)
            startActivity(loginIntent)
        }

    }
}