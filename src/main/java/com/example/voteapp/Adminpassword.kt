package com.example.voteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.voteapp.databinding.ActivityAdminpasswordBinding

class Adminpassword : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        val b=ActivityAdminpasswordBinding.inflate(layoutInflater)
        setContentView(b.root)
        val sp = getSharedPreferences("passwordinfo", MODE_PRIVATE)

        if (sp.getBoolean("isPasswordSet",false)==false){
            b.text.text="Please set an admin password"
            b.loginButton.text="set"
        }
        else{
            b.text.text="Please enter your admin password"
            b.loginButton.text="log in"
        }

        b.loginButton.setOnClickListener {
            val p=b.passwordbox.text.toString()
            val spa=getSharedPreferences("passwordinfo", MODE_PRIVATE)
            val editor=spa.edit()
            if (spa.getBoolean("isPasswordSet",false)==false){
                editor.putString("adminPassword", p)
                editor.putBoolean("isPasswordSet",true)
                editor.apply()


                val i= Intent(this, BoothSelection::class.java)
                startActivity(i)
            }
            else {
                if(p==spa.getString("adminPassword","")){
                    val i= Intent(this, Adminui::class.java)
                    startActivity(i)
                }
                else{
                    Toast.makeText(this,"Sorry! Wrong password",Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}