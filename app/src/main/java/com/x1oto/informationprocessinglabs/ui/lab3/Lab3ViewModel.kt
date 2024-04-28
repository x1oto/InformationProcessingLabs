package com.x1oto.informationprocessinglabs.ui.lab3

import kotlin.math.*
import androidx.lifecycle.ViewModel

class Lab3ViewModel : ViewModel() {

    fun calculateDFT(x: DoubleArray): DoubleArray {
        val N = x.size
        val X = DoubleArray(N)

        for (k in 0 until N) {
            var sumReal = 0.0
            var sumImaginary = 0.0
            for (n in 0 until N) {
                val angle = -2 * PI * k * n / N
                sumReal += x[n] * cos(angle)
                sumImaginary += x[n] * sin(angle)
            }
            X[k] = sqrt(sumReal * sumReal + sumImaginary * sumImaginary)
        }

        return X
    }

    fun calculateInverseDFT(X: DoubleArray): DoubleArray {
        val N = X.size
        val x = DoubleArray(N)

        for (n in 0 until N) {
            var sumReal = 0.0
            var sumImaginary = 0.0
            for (k in 0 until N) {
                val angle = 2 * PI * k * n / N
                sumReal += X[k] * cos(angle)
                sumImaginary += X[k] * sin(angle)
            }
            x[n] = (sumReal / N)
        }

        return x
    }
}