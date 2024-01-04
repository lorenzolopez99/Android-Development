package com.example.wordle

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Color.argb
import android.graphics.Color.rgb
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.SpannableStringBuilder
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.graphics.blue
import androidx.core.graphics.green
import androidx.core.graphics.red
import androidx.core.text.color
import com.example.wordle.FourLetterWordList.getRandomFourLetterWord

class MainActivity : AppCompatActivity() {
    var attempt: Int = 1
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var answer = getRandomFourLetterWord()
        val ans: TextView = findViewById(R.id.answer)
        ans.setText(answer)
    }

    fun reset(v: View){
        val retry: Button = v.findViewById(R.id.retry)
        val ans: TextView = findViewById(R.id.answer)
        var txt: TextView

        for (i in 1..3) {
            txt = findViewById(getResources().getIdentifier("guess"+i,"id",getPackageName()))
            txt.setText("")
        }
        attempt = 1

        retry.visibility = View.INVISIBLE
        ans.visibility = View.INVISIBLE
        var answer = getRandomFourLetterWord()
        ans.setText(answer)


        val vg : ViewGroup = findViewById(R.id.layout_cons)
        for (i in 0..vg.getChildCount()){
            var view = vg.getChildAt(i)
            if (view is Button){
                view.backgroundTintList = retry.backgroundTintList
            }
        }
    }



    fun type(v: View){

        if (attempt > 3) return
        val btn: Button = v.findViewById(v.id)
        val retry: Button = findViewById(R.id.retry)
        val guess: TextView = findViewById(getResources().getIdentifier("guess"+attempt,"id",getPackageName()))
        val guess_text = guess.text.toString()
        val ans: TextView = findViewById(R.id.answer)

        if (btn.text.toString() == "DEL" && ans.visibility == View.INVISIBLE ){
            guess.setText(guess_text.dropLast(1))
            return
        }

        if (guess_text.length != 4 && btn.text.toString() == "ENTER"){
            Toast.makeText(this, "Guess is not long enough!", Toast.LENGTH_SHORT).show()
            return
        }

        if (guess_text.length == 4 && btn.text.toString() == "ENTER"){
            //submit answer
            for (c in guess_text){

            }
            attempt+=1
            var check = checkGuess(guess_text)
            var checked = SpannableStringBuilder()

            for (i in 0..3){
                //change colors of guess and tint buttons according to correctness
                var b: Button = findViewById(getResources().getIdentifier("button_"+guess_text[i],"id",getPackageName()))
                var color: Int
                if (check[i] == 'O')
                    color = rgb(92,142,72)
                else if (check[i] == '+')
                    color = rgb(176,159,40)
                else
                    color = rgb(120, 120, 120)

                    checked.color(color, { append(guess_text[i]) })
                    b.backgroundTintList = ColorStateList.valueOf(argb(230,color.red,color.green,color.blue))
                }

            guess.setText(checked)

            if (guess_text == ans.text.toString()){
                //Success
                Toast.makeText(this, "Good Job!", Toast.LENGTH_SHORT).show()
                return
            }

            if (attempt == 4) {
                Toast.makeText(this, "Better Luck Next Time!", Toast.LENGTH_SHORT).show()
                ans.visibility = View.VISIBLE
                retry.visibility = View.VISIBLE
            }
            return
        }

        if (guess_text.length < 4){
            //Adds character to guess
            guess.setText(guess_text+btn.text.toString())
            return
        }
    }

    fun checkGuess(guess: String) : String {
        //If answer is "Town", but guess is "Trot", both T's will be highlighted
        val ans: TextView = findViewById(R.id.answer)

        var result = ""
        for (i in 0..3) {
            if (guess[i] == ans.getText()[i]) {
                result += "O"
            }
            else if (guess[i] in ans.getText()) {
                result += "+"
            }
            else {
                result += "X"
            }
        }
        return result
    }
}


