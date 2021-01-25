package com.example.mycalculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
class MainActivity : AppCompatActivity() {
    var lastNumber=false
    var lastDecimal=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun btn(view:View){
        textinput.append((view as Button).text)
        lastNumber=true

    }
    fun clear(view: View){
        textinput.text=""
        lastNumber=false
        lastDecimal=false
    }
    fun decimal(view: View) {
    if(lastNumber && !lastDecimal)
    {
        textinput.append(".")
        lastDecimal=true
    }
    }
    private fun CheckOperator(value: String):Boolean{
        return if (value.startsWith("-"))
            false
        else
           value.contains("+")|| value.contains("-")|| value.contains("*")||
                    value.contains("/")

    }
    fun operator(view: View) {
        if (lastNumber && !CheckOperator(textinput.text.toString())) {
            textinput.append((view as Button).text)
            lastDecimal = false
            lastNumber = false
        }
    }
    fun equals(view: View){
        var input=textinput.text.toString()
        var prefix=""
        if (lastNumber){
            try {
                if(input.startsWith("-")){
                    prefix="-"
                    input = input.substring(1)
                }
                if(input.contains("-")){
                    val split=input.split("-")
                    var x=split[0]
                    var y=split[1]
                    if (!prefix.isEmpty()){
                        x= prefix + x
                    }
                    textinput.text=DealwithZero((x.toDouble()-y.toDouble()).toString())                }
                else if(input.contains("/")){
                    val split=input.split("/")
                    var x=split[0]
                    var y=split[1]
                    if (!prefix.isEmpty()){
                        x= prefix + x
                    }
                    textinput.text=DealwithZero((x.toDouble()/y.toDouble()).toString())                }
                else if(input.contains("+")){
                    val split=input.split("+")
                    var x=split[0]
                    var y=split[1]
                    if (!prefix.isEmpty()){
                        x= prefix + x
                    }
                    textinput.text=DealwithZero((x.toDouble()+y.toDouble()).toString())                }
                else if(input.contains("*")){
                    val split=input.split("*")
                    var x=split[0]
                    var y=split[1]
                    if (!prefix.isEmpty()){
                        x= prefix + x
                    }
                    textinput.text=DealwithZero((x.toDouble()*y.toDouble()).toString())
                }
            }catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }

    }
    private fun DealwithZero(value: String):String{
        var result = value
       if (result.contains(".0"))
       {
           result=value.substring(0,value.length-2)
           if (result.endsWith("."))
               result=value

       }
        return result

    }
}