package com.example.voteapp

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.voteapp.databinding.ActivityViewresultBinding

class Viewresult : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val b=ActivityViewresultBinding.inflate(layoutInflater)
        setContentView(b.root)
        val sp:SharedPreferences? =getSharedPreferences("votecount", MODE_PRIVATE)
        val c= sp?.getInt("count",0)
        b.result.text= "Total number of casted votes: $c"
    }

}