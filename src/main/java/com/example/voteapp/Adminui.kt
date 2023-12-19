package com.example.voteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.voteapp.databinding.ActivityAdminuiBinding

class Adminui : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val b=ActivityAdminuiBinding.inflate(layoutInflater)
        setContentView(b.root)
        b.viewButton.setOnClickListener{
            val voteIntent = Intent(this, Viewresult::class.java)
            startActivity(voteIntent)
        }
        b.generateButton.setOnClickListener{
            val sp = getSharedPreferences("passwordinfo", MODE_PRIVATE)
            val e= sp.edit()
            e.putBoolean("isPasswordSet",false)
            e.apply()
            Toast.makeText(this,"Voting has been ended",Toast.LENGTH_LONG).show()
        }
        b.cancelButton.setOnClickListener{
            val i=Intent(this,MainActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK )
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(i)
        }

    }

}