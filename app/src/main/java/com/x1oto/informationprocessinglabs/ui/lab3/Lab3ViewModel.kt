package com.x1oto.informationprocessinglabs.ui.lab3

import androidx.lifecycle.ViewModel
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

class Lab3ViewModel : ViewModel() {
    data class Complex(val real: Double, val imaginary: Double)

    fun calculateDFT(x: DoubleArray): Array<Complex> {
        val N = x.size
        val X = Array(N) { Complex(0.0, 0.0) }

        for (k in 0 until N) {
            var sumReal = 0.0
            var sumImaginary = 0.0
            for (n in 0 until N) {
                val angle = 2 * PI * k * n / N
                sumReal += x[n] * cos(angle)
                sumImaginary -= x[n] * sin(angle)
            }
            X[k] = Complex(sumReal, sumImaginary)
        }

        return X
    }


    fun calculateInverseDFT(X: Array<Complex>): DoubleArray {
        val N = X.size
        val x = DoubleArray(N)

        for (n in 0 until N) {
            var sum = 0.0
            for (k in 0 until N) {
                val angle = 2 * PI * k * n / N
                val cosValue = cos(angle)
                val sinValue = sin(angle)
                sum += X[k].real * cosValue - X[k].imaginary * sinValue
            }
            x[n] = sum / N
        }

        return x
    }
}
