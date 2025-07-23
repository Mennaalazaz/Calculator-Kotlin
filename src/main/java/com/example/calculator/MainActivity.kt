package com.example.calculator

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import com.example.calculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var placeholder: TextView
    private lateinit var result: TextView


    fun appendInExpression(text: String){

        if(text != "CE") {
            placeholder.append(text)
        }
        else{ // if the user press CE , clear the placeholder and result
            placeholder.text = ""
            result.text = ""
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        supportActionBar?.hide() // hide the title bar

        // Get References to the views using binding instead of findViewById()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // take reference to the placeholder ( final answer place )
        placeholder = binding.placeholder
        result = binding.result

        binding.num0.setOnClickListener { appendInExpression("0") }
        binding.num1.setOnClickListener { appendInExpression("1") }
        binding.num2.setOnClickListener { appendInExpression("2") }
        binding.num3.setOnClickListener { appendInExpression("3") }
        binding.num4.setOnClickListener { appendInExpression("4") }
        binding.num5.setOnClickListener { appendInExpression("5") }
        binding.num6.setOnClickListener { appendInExpression("6") }
        binding.num7.setOnClickListener { appendInExpression("7") }
        binding.num8.setOnClickListener { appendInExpression("8") }
        binding.num9.setOnClickListener { appendInExpression("9") }
        binding.CE.setOnClickListener { appendInExpression("CE") }
        binding.openBracket.setOnClickListener { appendInExpression("(") }
        binding.closeBracket.setOnClickListener { appendInExpression(")") }
        binding.divide.setOnClickListener { appendInExpression("/") }
        binding.multiply.setOnClickListener { appendInExpression("*") }
        binding.subtract.setOnClickListener { appendInExpression("-") }
        binding.plus.setOnClickListener { appendInExpression("+") }
        binding.dot.setOnClickListener { appendInExpression(".") }

        binding.Back.setOnClickListener {  // On pressing back, remove the last character from the placeholder
            placeholder.text = placeholder.text.substring(0, placeholder.text.length - 1)
        }
        binding.equal.setOnClickListener {
            try {
                val expression = ExpressionBuilder(placeholder.text.toString()).build()
                var answer: String = expression.evaluate().toString()

                // if answer is 5.0 remove .0 & make it 5
                if (answer.endsWith(".0"))
                    answer = answer.substring(0, answer.length - 2)

                result.text = answer

                placeholder.text = ""
            }
            catch (e: Exception){
                result.text = "Error"
            }
        }

    }
}