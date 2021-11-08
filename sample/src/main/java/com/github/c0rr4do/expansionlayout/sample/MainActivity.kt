package com.github.c0rr4do.expansionlayout.sample

import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.SwitchCompat
import androidx.core.widget.doOnTextChanged
import com.github.c0rr4do.expansionlayout.ExpansionLayout
import com.github.c0rr4do.expansionlayout.ExpansionLayoutGroup
import com.github.c0rr4do.expansionlayout.NegativeSizeMode

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val expansionLayout = findViewById<ExpansionLayout>(R.id.expansionLayout)
        val expansionLayout2 = findViewById<ExpansionLayout>(R.id.expansionLayout2)

        val expansionLayoutGroup = ExpansionLayoutGroup()
        expansionLayoutGroup.singleSelect = true
        expansionLayoutGroup.add(expansionLayout)
        expansionLayoutGroup.add(expansionLayout2)

        val switchToggleOnHeaderClick = findViewById<SwitchCompat>(R.id.switchToggleOnHeaderClick)
        switchToggleOnHeaderClick.setOnCheckedChangeListener { _, checked ->
            expansionLayout.toggleOnHeaderClick = checked
        }

        val editTextExpansionDuration = findViewById<AppCompatEditText>(R.id.editTextExpansionDuration)
        editTextExpansionDuration.doOnTextChanged { text, _, _, _ ->
            val newDuration = text.toString().toLongOrNull()
            if (newDuration != null) {
                expansionLayout.expansionDuration = newDuration
            }
        }

        val editTextCollapsionDuration = findViewById<AppCompatEditText>(R.id.editTextCollapsionDuration)
        editTextCollapsionDuration.doOnTextChanged { text, _, _, _ ->
            expansionLayout.collapsionDuration = text.toString().toLongOrNull()
        }

        val radioGroupNegativeSizeMode = findViewById<RadioGroup>(R.id.radioGroupNegativeSizeMode)
        radioGroupNegativeSizeMode.setOnCheckedChangeListener { _, id ->
            when (id) {
                R.id.radioButtonKeep -> {
                    expansionLayout.negativeSizeMode = NegativeSizeMode.KEEP
                }
                R.id.radioButtonAbs -> {
                    expansionLayout.negativeSizeMode = NegativeSizeMode.ABS
                }
                R.id.radioButtonCeilToZero -> {
                    expansionLayout.negativeSizeMode = NegativeSizeMode.CEIL_TO_ZERO
                }
            }
        }

        val radioGroupInterpolator = findViewById<RadioGroup>(R.id.radioGroupInterpolator)
        radioGroupInterpolator.setOnCheckedChangeListener { _, id ->
            when (id) {
                R.id.radioButtonOvershoot -> {
                    expansionLayout.expansionInterpolator =
                        AnimationUtils.loadInterpolator(applicationContext, android.R.anim.overshoot_interpolator)
                }
                R.id.radioButtonLinear -> {
                    expansionLayout.expansionInterpolator =
                        AnimationUtils.loadInterpolator(applicationContext, android.R.anim.linear_interpolator)
                }
            }
        }
    }
}