package com.example.wishlist

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers.IO

class MainActivity : AppCompatActivity() {
    lateinit var entries: MutableList<DisplayArticle>
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val wishlistRv = findViewById<RecyclerView>(R.id.wishlistRv)

        //entries = EntryFetcher.getEntries()
        val adapter = EntryAdapter(entries)
        wishlistRv.adapter = adapter


        wishlistRv.layoutManager = LinearLayoutManager(this)
    }

    fun submit(v:View){
        fun View.hideKeyboard() {
            val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(windowToken, 0)
        }
        val nameTv: TextView = findViewById(R.id.name_input)
        val priceTv: TextView = findViewById(R.id.price_input)

        val name = nameTv.text.toString()
        val price = priceTv.text.toString()

        if (name.isEmpty()){
            Toast.makeText(this, "Enter a name for your food!", Toast.LENGTH_SHORT).show()
            return
        }
        if (price.isEmpty()){
            Toast.makeText(this, "Enter a calorie count for your food!", Toast.LENGTH_SHORT).show()
            return
        }

        entries.add(DisplayArticle(name,price.toInt()))
        nameTv.text=""
        priceTv.text=""
        v.hideKeyboard()
    }



}