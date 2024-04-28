package com.x1oto.informationprocessinglabs.ui.lab2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.x1oto.informationprocessinglabs.databinding.FragmentLab2Binding


class Lab2Fragment : Fragment() {

    private var _binding: FragmentLab2Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val lab2ViewModel = ViewModelProvider(this)[Lab2ViewModel::class.java]

        _binding = FragmentLab2Binding.inflate(inflater, container, false)
        val root: View = binding.root

        setupOriginalSignalButton(lab2ViewModel)
        setupScaleButton(lab2ViewModel)
        setupReverseButton(lab2ViewModel)
        setupShiftButton(lab2ViewModel)
        setupExtendButton(lab2ViewModel)

        return root
    }

    private fun setupOriginalSignalButton(viewModel: Lab2ViewModel) {
        binding.originalSignalBt.setOnClickListener {
            val signal = getSignal()
            println(signal)
            buildChart(signal, "Original signal")
        }
    }

    private fun setupScaleButton(viewModel: Lab2ViewModel) {
        binding.scaleBt.setOnClickListener {
            val signal = getSignal()
            val coefficientOrShift = getCoefficientOrShift()

            val modifiedSignal = viewModel.scaleSignal(coefficientOrShift, signal)
            println(coefficientOrShift)
            buildChart(modifiedSignal, "Scale signal")
        }
    }

    private fun setupReverseButton(viewModel: Lab2ViewModel) {
        binding.reverseBt.setOnClickListener {
            val signal = getSignal()

            val modifiedSignal = viewModel.reverseSignal(signal)
            buildChart(modifiedSignal, "Reversed signal")
        }
    }

    private fun setupShiftButton(viewModel: Lab2ViewModel) {
        binding.shiftBt.setOnClickListener {
            val signal = getSignal()
            val coefficientOrShift = getCoefficientOrShift()

            val modifiedSignal = viewModel.shiftSignal(coefficientOrShift, signal)
            println(coefficientOrShift)
            buildChart(modifiedSignal, "Shift signal")
        }
    }

    private fun setupExtendButton(viewModel: Lab2ViewModel) {
        binding.extendBt.setOnClickListener {
            val signal = getSignal()

            val modifiedSignal = viewModel.extendSignal(signal)
            buildChart(modifiedSignal, "Extend signal")
        }
    }

    private fun getSignal(): List<Float> {
        val originalSignal = binding.originalSignalEditText.text.toString()
        val numbers = originalSignal.split("").filter { it.isNotBlank() }
            .mapNotNull { it.toFloatOrNull() }

        return numbers
    }

    private fun getCoefficientOrShift() = binding.coefficientOrShiftEditText.text.toString().toFloat()

    private fun buildChart(originalSignal: List<Float>, name: String) {
        val lineChart = binding.secondLabChartView

        val entries = ArrayList<Entry>()
        originalSignal.forEachIndexed { index, i ->
            entries.add(Entry(index.toFloat(), i))
        }

        val dataSet = LineDataSet(entries, name)

        val data = LineData(dataSet)
        lineChart.data = data

        lineChart.invalidate()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
