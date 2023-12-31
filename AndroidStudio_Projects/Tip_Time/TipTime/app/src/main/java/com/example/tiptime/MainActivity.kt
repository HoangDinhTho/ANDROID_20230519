package com.example.tiptime

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.tiptime.databinding.ActivityMainBinding
import com.google.android.material.switchmaterial.SwitchMaterial
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener { calculateTip() }
        binding.costOfServiceEditText.setOnKeyListener { view, keyCode, _ ->
            handleKeyEvent(
                view,
                keyCode
            )
        }
//        binding.optionTwentyPercent.setOnCheckedChangeListener { buttonView, isChecked -> handleOnCheckedChangeEvent(buttonView, isChecked) }
//        binding.optionEighteenPercent.setOnCheckedChangeListener { buttonView, isChecked -> handleOnCheckedChangeEvent(buttonView, isChecked) }
//        binding.optionFifteenPercent.setOnCheckedChangeListener { buttonView, isChecked -> handleOnCheckedChangeEvent(buttonView, isChecked) }
        binding.roundUpSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            handleOnCheckedChangeEvent(
                buttonView,
                isChecked
            )
        }
    }

    private fun calculateTip() {
        // Get the decimal value from the cost of service text field
        val stringInTextField = binding.costOfServiceEditText.text.toString()
        val cost = stringInTextField.toDoubleOrNull()
        if (cost == null) {
            displayTip(0.0)
            return
        }
        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }
        var tip = tipPercentage * cost
        if (binding.roundUpSwitch.isChecked) {
            tip = kotlin.math.ceil(tip)
        }
        displayTip(tip)
    }

    private fun displayTip(tip: Double) {
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }

    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        println("handleKeyEvent keyCode -> $keyCode")
        calculateTip()
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }

    private fun handleOnCheckedChangeEvent(view: View, isChecked: Boolean) {
        println("handleOnCheckedChangeEvent isChecked -> $isChecked")
        calculateTip()
    }
}