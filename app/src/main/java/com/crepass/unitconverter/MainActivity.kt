package com.crepass.unitconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.core.widget.addTextChangedListener
import com.crepass.unitconverter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var cmToM=true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        //뷰 바인딩을 쓰는 이유
        //다른 레이아웃의 id의 값이 중복이 생길 수 있기 때문에..

        val outputTextView=binding.outputTextView
        val outputUnitTextView=binding.outputUnitTextView
        val EditText=binding.editText
        val inputUnitTextView=binding.inputUnitTextView
        val swap=binding.transButton

        var inputNumber:Int=0

        //문자열이 변경이 되면 알려주는 메서드
        EditText.addTextChangedListener{text->
            inputNumber=if(!text.isNullOrEmpty()){
                text.toString().toInt()
            }else{
                0
            }
            Log.d("inputNumber",inputNumber.toString())

            if(cmToM){
                outputTextView.text=inputNumber.times(0.01).toString()

            }else{
                outputTextView.text=inputNumber.times(100).toString()
            }

        }
        swap.setOnClickListener {
            cmToM=!cmToM //or cmToM.not()
            if(cmToM){
                inputUnitTextView.text="cm"
                outputUnitTextView.text="m"
                outputTextView.text=inputNumber.times(0.01).toString()

            }else{
                inputUnitTextView.text="m"
                outputUnitTextView.text="cm"
                outputTextView.text=inputNumber.times(100).toString()
            }
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean("cmToM",cmToM)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        cmToM=savedInstanceState.getBoolean("cmToM")
        Log.d("cmToM",cmToM.toString())
        binding.inputUnitTextView.text=if(cmToM)"cm" else "m"
        binding.outputUnitTextView.text=if(cmToM)"m" else "cm"
        super.onRestoreInstanceState(savedInstanceState)
    }
}