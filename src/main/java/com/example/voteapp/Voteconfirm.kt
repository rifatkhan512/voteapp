package com.example.voteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.voteapp.databinding.ActivityVoteconfirmBinding

class Voteconfirm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val b=ActivityVoteconfirmBinding.inflate(layoutInflater)
        setContentView(b.root)

        val castid= intent.getStringExtra("castid")
        b.castidshow.text= "CastID: $castid"
        val sws = intent.getIntExtra("sws",0)
        val irs = intent.getIntExtra("irs",0)
        val cs = intent.getIntExtra("cs",0)
        val ss = intent.getIntExtra("ss",0)
        val pps = intent.getIntExtra("pps",0)
        val em1 = intent.getIntExtra("em1",0)
        val em = intent.getIntExtra("em",0)
        val em2 = intent.getIntExtra("em2",0)

        val swsname= when(sws){
            1-> "Samin Sadique Aurin"
            2-> "Junayed Ahmed"
            else ->"Not available"

        }
        val irsname=when(irs){
            1-> "Mirza Afnan Islam"
            2-> "Tanvir Ahmed"

            else ->"Not available"
        }
        val csname= when(cs){
            1->"Aniruddha Majumder"

            else->"Not available"
        }
        val ssname= when(ss){
            1-> "Mashfi Hossain"
            2-> "Debjoty Roy Pranto"
            3-> "Abyead Zarif Hassan"
            else->"Not available"
        }
        val ppsname= when(pps){
            1->"Maria Mim"
            else->"Not available"
        }
        val emname = when(em){
            1-> "Anika Begum"
            2-> "Muhtadi Al Tahmid"

            else ->0
        }
        val em1name= when(em1){
            1-> "M. Farhan Ishmam"
            2-> "Asraful islam Hemel"
            3-> "Samudra Jit Saha"
            4-> "MD. Abid Chowdhury"
            5-> "Md. Ajmain Istiak Apon"
            6-> "Md Nafiul Islam"
            else ->"Not available"
        }
        val em2name= when(em2){
            1-> "M. Farhan Ishmam"
            2-> "Asraful islam Hemel"
            3-> "Samudra Jit Saha"
            4-> "MD. Abid Chowdhury"
            5-> "Md. Ajmain Istiak Apon"
            6-> "Md Nafiul Islam"
            else ->" "
        }
        b.swsshow.text= "Social Welfare Secretary: $swsname"
        b.irsshow.text= "Information and Research Secretary: $irsname"
        b.csshow.text= "Cultural Secretary: $csname"
        b.ppsshow.text= "Printing and Publications Secretary: $ppsname"
        b.ssshow.text= "Sports Secretary: $ssname"
        b.emshow.text= "Executive Member(8th batch):\n $emname"
        b.em2show.text= "Executive Member(7th batch):\n $em1name\n $em2name"


        b.back.setOnClickListener{
            val intent= Intent(this,MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK )
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
    }


}