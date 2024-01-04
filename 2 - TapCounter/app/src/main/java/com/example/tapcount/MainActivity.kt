package com.example.tapcount

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    var counter = 0
    var inc = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun toaster(msg: String){
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(this, msg,duration)
        toast.show()
    }
    fun tapClick(view: View?){
        val tv1: TextView = findViewById(R.id.textView1)
        println("tap button tapped!")
        counter+=inc
        tv1.text = counter.toString()
        //toaster("TAPPED!")

        val btn2: Button = findViewById(R.id.button2)
        if (counter > 99) btn2.visibility = View.VISIBLE
        else btn2.visibility = View.GONE
    }

    fun upgradeClick(view: View?){
        val tv1: TextView = findViewById(R.id.textView1)
        println("upgrade button tapped!")
        counter-=100
        inc*=2
        tv1.text = counter.toString()
        toaster("UPGRADED!")

        val btn2: Button = findViewById(R.id.button2)
        if (counter > 99) btn2.visibility = View.VISIBLE
        else btn2.visibility = View.GONE
    }

}