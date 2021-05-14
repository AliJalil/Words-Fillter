package com.example.testapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var keywords = ArrayList<String>()
    var sentence = ""
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addWordsBtn.setOnClickListener {
            "${myWordsTv.text}, ${wordsTxt.text}".also { myWordsTv.text = it }
            keywords.add(wordsTxt.text.toString())
            wordsTxt.setText("")
        }


        getNewTxtBtn.setOnClickListener {
           var newK = ArrayList<String>()
            keywords.forEach { k ->
                newK.add(k.replace("\\B".toRegex(), "-"))
            }
            println(newK)
            sentence = inputTxt.text.toString()
            keywords.forEach {  k ->
                val newK = k.replace("\\B".toRegex(), "-")
                sentence = sentence.replace(k to newK)
            }

            outputTxt.setText(sentence)
        }
    }
}
fun String.replace(vararg replacements: Pair<String, String>): String {
    var result = this
    replacements.forEach { (k, r) -> result = result.replace(Regex("^$k\\s|\\s$k\\s|$k\\s|\\s$k$" ), " $r ") }
    return result
}