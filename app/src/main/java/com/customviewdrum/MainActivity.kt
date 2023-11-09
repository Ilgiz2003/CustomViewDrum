package com.customviewdrum

import android.graphics.Canvas
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import com.bumptech.glide.Glide
import com.customviewdrum.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var customDrum: CustomDrum
    private lateinit var rotateButton: Button
    private lateinit var sizeSeekBar: SeekBar
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rotateButton.setOnClickListener {
            val result = binding.customDrum.rotate()
            val randomWidth = (200..700).random()
            val randomHeight = (200..700).random()
            println( "result"+ result)
            if(result == 1 || result == 6 || result == 4){
                Glide.with(this)
                    .load("https://loremflickr.com/$randomWidth/$randomHeight")
                    .into(binding.image)
            } else {
                customDrum.drawText("Victory", Canvas())
            }
        }

        binding.resetButton.setOnClickListener {
            Glide.with(this)
                .clear(binding.image)
        }



        binding.sizeSeekBar.progress = 50
        binding.sizeSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val scale = progress / 50f
                customDrum.scaleX = scale
                customDrum.scaleY = scale
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }
}