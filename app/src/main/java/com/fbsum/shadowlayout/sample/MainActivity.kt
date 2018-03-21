package com.fbsum.shadowlayout.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val onSeekBarChangeListener = object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                this@MainActivity.onProgressChanged(seekBar, progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        }
        arrayOf(radiusSeekBar,
                cornerSeekBar,
                leftOffsetSeekBar,
                rightOffsetSeekBar,
                topOffsetSeekBar,
                bottomOffsetSeekBar)
                .forEach {
                    it.setOnSeekBarChangeListener(onSeekBarChangeListener)
                }

        showChildCheckBox.setOnClickListener {
            if (showChildCheckBox.isChecked) {
                roundedImageView.visibility = View.VISIBLE
            } else {
                roundedImageView.visibility = View.GONE
            }
        }
    }

    fun onProgressChanged(seekBar: SeekBar, progress: Int) {
        when (seekBar) {
            radiusSeekBar -> {
                coloredShadowLayout.setShadowRadius(progress)
                shadowLayout.setShadowRadius(progress)
            }
            cornerSeekBar -> {
                val corner = progress.toFloat()
                coloredShadowLayout.setShadowCorner(progress)
                shadowLayout.setShadowCorner(progress)
                roundedImageView.setCornerRadius(corner, corner, corner, corner)
            }
            leftOffsetSeekBar -> {
                coloredShadowLayout.setShadowLeftOffset(progress)
                shadowLayout.setShadowLeftOffset(progress)
            }
            rightOffsetSeekBar -> {
                coloredShadowLayout.setShadowRightOffset(progress)
                shadowLayout.setShadowRightOffset(progress)
            }
            topOffsetSeekBar -> {
                coloredShadowLayout.setShadowTopOffset(progress)
                shadowLayout.setShadowTopOffset(progress)
            }
            bottomOffsetSeekBar -> {
                coloredShadowLayout.setShadowBottomOffset(progress)
                shadowLayout.setShadowBottomOffset(progress)
            }
        }
    }
}
