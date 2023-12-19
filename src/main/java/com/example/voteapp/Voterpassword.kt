package com.example.voteapp

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast


import com.example.voteapp.databinding.ActivityVoterpasswordBinding

class Voterpassword : AppCompatActivity() {
//    private var passwords:ArrayList<String> = arrayListOf()
//    private val passwords1 = arrayListOf("16mqrw","182rwd","19hbit","19ng5g","1calyw","1cfhgh","1dqc3m","1eim9p","1g38jd","1k522t","1loxk3","1mgrqh",
//        "1q2fpz","1tehv4","1u9bfa","1vcxak","1vttli","1wvfwa","1wz25i","1yabtm")
//    private val passwords2 = arrayListOf("2173zu","21vbu2","23turv","24alun","24uim8","287v6m","28lxxd","2c2zsa","2eij5m","2elsmi","2g5mcx",
//    "2gv50q","2ixcx1","2o4gxf","2qcest","2qhu17","2rn69r","2xvddr","2z1znl","2zvoea")
//    private val passwords3 = arrayListOf("30x6d8","31r03u","327hep","36jc9s","36m99h","36vf4v","37fxpe","3bf88l","3ccapm","3cmn6b","3e45ex",
//    "3g9uwf","3lispc","3ljzni","3pnm73","3qv9jw","3v2mw6","3vpp9m","3w0es8","3y30zq")
//    private val passwords4 = arrayListOf("41zw7f","45njrw","45x95w","46miqc","478ap5","47l0rd","480bs6","4aopqw","4cgdbv","4dfvaq","4en56i","4fs49i",
//    "4gnf4t","4i8hk1","4jlzja","4mip0b","4r3plx","4sfh9w","4suqz2","4xriq2")

    override fun onCreate(savedInstanceState: Bundle?) {
        var passwords:ArrayList<String> = arrayListOf()
        val passwords1 = arrayListOf("16mqrw","182rwd","19hbit","19ng5g","1calyw","1cfhgh","1dqc3m","1eim9p","1g38jd","1k522t","1loxk3","1mgrqh",
            "1q2fpz","1tehv4","1u9bfa","1vcxak","1vttli","1wvfwa","1wz25i","1yabtm")
        val passwords2 = arrayListOf("2173zu","21vbu2","23turv","24alun","24uim8","287v6m","28lxxd","2c2zsa","2eij5m","2elsmi","2g5mcx",
            "2gv50q","2ixcx1","2o4gxf","2qcest","2qhu17","2rn69r","2xvddr","2z1znl","2zvoea")
        val passwords3 = arrayListOf("30x6d8","31r03u","327hep","36jc9s","36m99h","36vf4v","37fxpe","3bf88l","3ccapm","3cmn6b","3e45ex",
            "3g9uwf","3lispc","3ljzni","3pnm73","3qv9jw","3v2mw6","3vpp9m","3w0es8","3y30zq")
        val passwords4 = arrayListOf("41zw7f","45njrw","45x95w","46miqc","478ap5","47l0rd","480bs6","4aopqw","4cgdbv","4dfvaq","4en56i","4fs49i",
            "4gnf4t","4i8hk1","4jlzja","4mip0b","4r3plx","4sfh9w","4suqz2","4xriq2")

        super.onCreate(savedInstanceState)
        val b = ActivityVoterpasswordBinding.inflate(layoutInflater)
        //checkPreferences()
        setContentView(b.root)
        val sp: SharedPreferences = getSharedPreferences("boothid", MODE_PRIVATE)
        val boothid=sp.getInt("booth",0)
        passwords= when(boothid){
            1-> passwords1
            2->passwords2
            3->passwords3
            4->passwords4
            else-> passwords
        }
        //Log.d("passwords",passwords.toString())
        //Log.d("boothid",boothid.toString())
        b.loginButton.setOnClickListener {
            /*val sp2= getSharedPreferences("passwordsmarker", MODE_PRIVATE)
            val editor= sp2.edit()
            val password = b.passwordbox.text.toString()
            if (password in passwords) {
                if (!sp2.getBoolean(password, false)) {
                    editor.putBoolean(password, true)
                    editor.apply()
                    Toast.makeText(this, "Login Successful", Toast.LENGTH_LONG).show()

                    val vote = Intent(this, Voteui::class.java)
                    startActivity(vote)
                }else {
                    Toast.makeText(
                        this,
                        "Sorry,the Userid is used before",
                        Toast.LENGTH_LONG
                    ).show()
                }
            } else {
                Toast.makeText(
                    this,
                    "Sorry, wrong Userid",
                    Toast.LENGTH_LONG
                ).show()*/

            val name = b.passwordbox.text.toString()

            val batch = when(b.batch.checkedRadioButtonId){
                R.id.b8 -> 8
                R.id.b7 -> 7
                R.id.b6 -> 6
                R.id.b5 -> 5
                R.id.b4 -> 4
                R.id.b3 -> 3
                else -> 0
            }
            if (name==""){
                Toast.makeText(this,"You must enter a name",Toast.LENGTH_SHORT).show()
            }
            else {
                val vote = Intent(this, Voteui::class.java)
                vote.putExtra("name", name)
                vote.putExtra("batch", batch)
                startActivity(vote)
            }







        }



    }

    /*private fun checkPreferences(){
        val sp: SharedPreferences? = getSharedPreferences("passwords", MODE_PRIVATE)
        val editor=sp?.edit()
        for (password in passwords){
            if (!sp!!.contains(password)){
                editor?.putBoolean(password,false)
            }
        }
        editor?.apply()
    }*/
}