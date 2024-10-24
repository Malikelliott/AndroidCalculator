package project.n991610598.calculatorappbymalikelliott991610598

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var canAddOpp = false
    private var canAddDec = true
    private var isPos = true

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val working = findViewById<TextView>(R.id.calc)
        val final = findViewById<TextView>(R.id.disp)

        //Making the spinner
        val operands = arrayOf("+", "-", "/", "*","%")
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, operands)
        val mySpinner = findViewById<Spinner>(R.id.operand)
        mySpinner.adapter = arrayAdapter
        mySpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                //when operand is selected, add it to the calculation string
                val op = operands[position]; working.append(op); canAddOpp = false
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                //selected.text = "select an operand"
            }
        }

        //When the clear button is clicked//
        findViewById<Button>(R.id.clear_text).setOnClickListener{
            working.text= ""; final.text= ""; canAddOpp = false;
            //add a toast here
        }

        //When Radio Buttons are clicked//
        findViewById<RadioButton>(R.id.radioButton3).setOnClickListener{
            findViewById<ConstraintLayout>(R.id.clayout).setBackgroundColor(resources.getColor(R.color.water))
        }
        findViewById<RadioButton>(R.id.radioButton2).setOnClickListener{
            findViewById<ConstraintLayout>(R.id.clayout).setBackgroundColor(resources.getColor(R.color.acid))
        }
        findViewById<RadioButton>(R.id.radioButton).setOnClickListener{
            findViewById<ConstraintLayout>(R.id.clayout).setBackgroundColor(resources.getColor(R.color.lava))
        }

        //When the backspace button is clicked//
        findViewById<Button>(R.id.backspace).setOnClickListener{
            if (working.length() > 0){ working.text = working.text.subSequence(0, working.length() - 1) }
        }

        //When the decimal button is clicked//
        findViewById<Button>(R.id.decimal).setOnClickListener{ working.append(".")
        }

        findViewById<Button>(R.id.one).setOnClickListener{ working.append("1")}
        findViewById<Button>(R.id.two).setOnClickListener{ working.append("2")}
        findViewById<Button>(R.id.three).setOnClickListener{ working.append("3")}
        findViewById<Button>(R.id.four).setOnClickListener{ working.append("4")}
        findViewById<Button>(R.id.five).setOnClickListener{ working.append("5")}
        findViewById<Button>(R.id.six).setOnClickListener{ working.append("6")}
        findViewById<Button>(R.id.seven).setOnClickListener{ working.append("7")}
        findViewById<Button>(R.id.eight).setOnClickListener{ working.append("8")}
        findViewById<Button>(R.id.nine).setOnClickListener{ working.append("9")}
        findViewById<Button>(R.id.zero).setOnClickListener{ working.append("0")}


        //when = is pressed
        findViewById<Button>(R.id.equals).setOnClickListener{

            val list = mutableListOf<Any>()
            var currDig = ""
            for(character in working.text){
                if(character.isDigit() || character == '.')
                    currDig += character
                else{
                    list.add(currDig.toFloat())
                    currDig = ""
                    list.add(character)
                }
            }

            if(currDig != ""){
                list.add(currDig.toFloat())
            }

            if (list.size == 1){
                final.text = list[0].toString()
            }
            if (list[1] == '*'){
                val answer = (list[0] as Float) * (list[2] as Float)
                final.text = answer.toString()
            }
            if (list[1] == '/'){
                val answer = list[0] as Float / list[2] as Float
                final.text = answer.toString()
            }
            if (list[1] == '+'){
                val answer = list[0] as Float + list[2] as Float
                final.text = answer.toString()
            }
            if (list[1] == '-'){
                val answer = list[0] as Float - list[2] as Float
                final.text = answer.toString()
            }
            if (list[1] == '%'){
                val answer = list[0] as Float % list[2] as Float
                final.text = answer.toString()
            }
            if (working.text == ""){
                val answer = 0
                final.text = answer.toString()
            }
            /*val list2 = mutableListOf<Any>()
            while (list.contains('*') || list.contains('/')){
                var restartIndex = list.size

                for(i in list.indices){
                    if (list[i] is Char && i != list.lastIndex && i < restartIndex){
                        val operator = list[i]
                        val prevDig = list[i - 1] as Float
                        val nextDig = list[i + 1] as Float
                        when (operator){
                            '*' -> {list2.add(prevDig * nextDig)
                                restartIndex = i + 1}
                            '/' -> {list2.add(prevDig / nextDig)
                                restartIndex = i + 1}
                            else -> {list2.add(prevDig)
                                list2.add(operator)}
                        }
                    }
                    if (i > restartIndex)
                        list2.add(list[i])
                }
            }

            var result = list2[0] as Float

            for(i in list2.indices){
                if (list2[i] is Char && i != list2.lastIndex) {
                    val operator = list2[i]
                    val nextDigit = list2[i + 1] as Float
                    if (operator == '+') result += nextDigit
                    if (operator == '-') result -= nextDigit
                }
                final.text= result.toString()
            }*/
        }//end of eq listener(button)
    }// end of oncreate
}// end of class