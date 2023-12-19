package com.example.voteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.voteapp.databinding.ActivityBoothSelectionBinding

class BoothSelection : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val b=ActivityBoothSelectionBinding.inflate(layoutInflater)
        setContentView(b.root)

        b.confirm.setOnClickListener{
            val sp= getSharedPreferences("boothid", MODE_PRIVATE)
            val editor=sp.edit()
            val booth = when(b.booth.checkedRadioButtonId){
                R.id.b1-> 1
                R.id.b2->2
                R.id.b3 ->3
                R.id.b4-> 4
                else -> 0

            }
            if (booth==0){
                Toast.makeText(this,"Please select one of them",Toast.LENGTH_LONG).show()
            }
            else{
                editor.putInt("booth",booth)
                editor.apply()
                val intent= Intent(this,MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK )
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
                finish()
            }
        }
    }
}