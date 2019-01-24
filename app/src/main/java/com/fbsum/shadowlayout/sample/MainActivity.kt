package com.fbsum.shadowlayout.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*
import com.flask.colorpicker.ColorPickerView
import com.flask.colorpicker.builder.ColorPickerDialogBuilder


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
        arrayOf(blurSeekBar,
                roundSeekBar,
                leftOffsetSeekBar,
                rightOffsetSeekBar,
                topOffsetSeekBar,
                bottomOffsetSeekBar,
                dxOffsetSeekBar,
                dyOffsetSeekBar)
                .forEach {
                    it.setOnSeekBarChangeListener(onSeekBarChangeListener)
                }

        showChildCheckBox.setOnClickListener {
            if (showChildCheckBox.isChecked) {
                superTextView.visibility = View.VISIBLE
            } else {
                superTextView.visibility = View.GONE
            }
        }

        autoDarkenCheckBox.setOnClickListener {
            coloredShadowLayout.setShadowAutoDarken(autoDarkenCheckBox.isChecked)
        }

        colorImageView.setOnClickListener {
            ColorPickerDialogBuilder
                    .with(this)
                    .setTitle("Choose color")
                    .initialColor(resources.getColor(R.color.colorAccent))
                    .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                    .density(12)
                    .setPositiveButton("ok") { dialog, selectedColor, allColors ->
                        colorImageView.setBackgroundColor(selectedColor)
                        superTextView.setSolid(selectedColor)
                        coloredShadowLayout.setShadowColor(selectedColor)
                    }
                    .setNegativeButton("cancel") { dialog, which -> }
                    .build()
                    .show()
        }
    }

    fun onProgressChanged(seekBar: SeekBar, progress: Int) {
        val px = dip2px(progress).toInt()
        when (seekBar) {
            blurSeekBar -> {
                blurTextView.text = progress.toString()
                coloredShadowLayout.setShadowBlur(px)
            }
            roundSeekBar -> {
                roundTextView.text = progress.toString()
                val corner = px.toFloat()
                coloredShadowLayout.setShadowRound(px)
                superTextView.setCorner(corner)
            }
            leftOffsetSeekBar -> {
                leftOffsetTextView.text = progress.toString()
                coloredShadowLayout.setShadowOffsetLeft(px)
            }
            rightOffsetSeekBar -> {
                rightOffsetTextView.text = progress.toString()
                coloredShadowLayout.setShadowOffsetRight(px)
            }
            topOffsetSeekBar -> {
                topOffsetTextView.text = progress.toString()
                coloredShadowLayout.setShadowOffsetTop(px)
            }
            bottomOffsetSeekBar -> {
                bottomOffsetTextView.text = progress.toString()
                coloredShadowLayout.setShadowOffsetBottom(px)
            }
            dxOffsetSeekBar -> {
                dxOffsetTextView.text = progress.toString()
                coloredShadowLayout.setShadowOffsetDx(px)
            }
            dyOffsetSeekBar -> {
                dyOffsetTextView.text = progress.toString()
                coloredShadowLayout.setShadowOffsetDy(px)
            }
        }
    }

    private fun dip2px(dpValue: Int): Float {
        val dm = resources.displayMetrics
        val scale = dm.density
        return (dpValue * scale + 0.5f).toInt().toFloat()
    }
}
