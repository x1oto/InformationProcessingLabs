package com.x1oto.informationprocessinglabs.ui.lab2

import androidx.lifecycle.ViewModel

class Lab2ViewModel : ViewModel() {

    fun scaleSignal(coefficient: Float, signal: List<Float>) = signal.map { it * coefficient }

    fun reverseSignal(signal: List<Float>) = signal.reversed()

    fun shiftSignal(shift: Float, signal: List<Float>): List<Float> = signal.drop(shift.toInt()) + signal.take(
        shift.toInt()
    )

    fun extendSignal(signal: List<Float>) = signal + signal
}