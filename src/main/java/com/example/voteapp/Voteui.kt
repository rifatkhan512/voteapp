package com.example.voteapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.voteapp.databinding.ActivityVoteuiBinding
import java.io.File
import java.io.FileOutputStream
import java.io.FileWriter
import java.util.*


class Voteui : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val b = ActivityVoteuiBinding.inflate(layoutInflater)
        setContentView(b.root)
        var name = intent.getStringExtra("name")
        val batch = intent.getIntExtra("batch",0)
        name = "$name($batch"+"th batch)"

        if (batch == 8){
            b.cs.visibility= View.GONE
            b.irs.visibility= View.GONE
            b.ss.visibility= View.GONE
            b.pps.visibility= View.GONE
            b.sws.visibility= View.GONE
            b.em21.visibility= View.GONE
            b.em22.visibility= View.GONE
            b.em23.visibility= View.GONE
            b.em24.visibility= View.GONE
            b.em25.visibility= View.GONE
            b.em26.visibility= View.GONE
        }

        b.submitButton.setOnClickListener {
            castVote(b,name,batch)


        }
    }

    private fun castVote(b: ActivityVoteuiBinding,name:String,batch:Int) {
        val sp = getSharedPreferences("castid", MODE_PRIVATE)
        val editor = sp.edit()
        val state = Environment.getExternalStorageState()
        if (Environment.MEDIA_MOUNTED == state) {
            if (checkPermission()) {
                val sdcard = getExternalFilesDir("voteapp")

                val cs = when (b.cs.checkedRadioButtonId) {
                    R.id.cs1 -> 1


                    else -> 0
                }
                val sws = when (b.sws.checkedRadioButtonId) {
                    R.id.sws1 -> 1
                    R.id.sws2 -> 2
                    else -> 0

                }
                val pps = when (b.pps.checkedRadioButtonId) {
                    R.id.pps1 -> 1

                    else -> 0
                }
                val irs = when (b.irs.checkedRadioButtonId) {
                    R.id.irs1 -> 1
                    R.id.irs2 -> 2

                    else -> 0
                }
                val ss = when (b.ss.checkedRadioButtonId) {
                    R.id.ss1 -> 1
                    R.id.ss2 -> 2
                    R.id.ss3 -> 3
                    else -> 0
                }
                val em = when (b.em.checkedRadioButtonId) {
                    R.id.em1 -> 1
                    R.id.em2 -> 2

                    else -> 0
                }


                val em2 = arrayListOf<Int>()
                if (b.em21.isChecked) {
                    em2.add(1)
                }
                if (b.em22.isChecked) {
                    em2.add(2)
                }
                if (b.em23.isChecked) {
                    em2.add(3)
                }
                if (b.em24.isChecked) {
                    em2.add(4)
                }
                if (b.em25.isChecked) {
                    em2.add(5)
                }
                if (b.em26.isChecked) {
                    em2.add(6)
                }
                if (batch == 8){
                    em2.add(0)
                    em2.add(0)
                }
                if (em2.size != 2) {
                    Toast.makeText(
                        this,
                        "You have to select exactly two executive members",
                        Toast.LENGTH_LONG
                    ).show()
                    return
                }
                var castid = ((10001..99999).random()).toString()
                while (true) {

                    if (!sp.getBoolean(castid, false)) {
                        editor.putBoolean(castid, true)
                        editor.apply()
                        break
                    } else castid = ((10001..99999).random()).toString()
                }


                val spb =getSharedPreferences("boothid", MODE_PRIVATE)
                val filename= when(spb.getInt("booth",0)){
                    1-> "booth1.vr"
                    2-> "booth2.vr"
                    3-> "booth3.vr"
                    4-> "booth4.vr"
                    else-> "sample.txt"
                }
                val boothid= when(spb.getInt("booth",0)){
                    1-> "1"
                    2-> "2"
                    3-> "3"
                    4-> "4"
                    else-> "0"
                }
                var data= "$boothid $castid $name $sws $cs $irs $pps $ss $em"
                for (i in em2) {
                    data += " $i"
                }


                val file = File(sdcard, filename)

                try {
                    val os = FileWriter(file, true)
                    os.write(data + "\n")
                    os.close()
                    Toast.makeText(this@Voteui, "Vote done", Toast.LENGTH_LONG).show()
                    countup()
                    val intent = Intent(this@Voteui, Voteconfirm::class.java)
                    intent.putExtra("castid",castid)
                    intent.putExtra("sws",sws)
                    intent.putExtra("cs",cs)
                    intent.putExtra("irs",irs)
                    intent.putExtra("pps",pps)
                    intent.putExtra("ss",ss)
                    intent.putExtra("em",em)
                    intent.putExtra("em1",em2[0])
                    intent.putExtra("em2",em2[1])

                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK )
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

                    startActivity(intent)
                    finish()


                } catch (e: Exception) {
                    e.printStackTrace()
                    Toast.makeText(this@Voteui, "some error", Toast.LENGTH_LONG).show()
                }
            } else {
                requestPermission() // Code for permission
            }
        }
    }


    private fun checkPermission(): Boolean {
        val result = ContextCompat.checkSelfPermission(
            this@Voteui,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        return result == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this@Voteui,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        ) {
            Toast.makeText(
                this@Voteui,
                "Write External Storage permission allows us to create files. Please allow this permission in App Settings.",
                Toast.LENGTH_LONG
            ).show()
        } else {
            ActivityCompat.requestPermissions(
                this@Voteui,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 100
            )
        }
    }

    private fun countup() {
        val sp = getSharedPreferences("votecount", MODE_PRIVATE)
        val c = sp.getInt("count", 0)
        val editor = sp.edit()
        editor.putInt("count", c + 1)
        editor.apply()
    }
}
