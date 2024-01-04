package com.example.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun buttonClick(view: View?){
        val text = "Hello to you, too!"
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(this, text,duration)
        toast.show()
        println("button clicked!")
    }
}