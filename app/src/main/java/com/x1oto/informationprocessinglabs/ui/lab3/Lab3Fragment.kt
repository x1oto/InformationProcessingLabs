package com.x1oto.informationprocessinglabs.ui.lab3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.x1oto.informationprocessinglabs.databinding.FragmentLab3Binding

class Lab3Fragment : Fragment() {

    private var _binding: FragmentLab3Binding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val lab3ViewModel =
                ViewModelProvider(this).get(Lab3ViewModel::class.java)

        _binding = FragmentLab3Binding.inflate(inflater, container, false)
        val root: View = binding.root

        setupCalculateButton(lab3ViewModel)

        return root
    }

    private fun setupCalculateButton(viewModel: Lab3ViewModel) {
        binding.calculateBt.setOnClickListener {
            val x = getSequence()
            var result = ""

            val directDFT = viewModel.calculateDFT(x)
            result += "Пряме ДПФ:\n"
            directDFT.forEachIndexed { index, value ->
                result += "X[$index] = $value\n"
            }

            val inverseDFT = viewModel.calculateInverseDFT(directDFT)
            result +="\nОбернене ДПФ:\n"
            inverseDFT.forEachIndexed { index, value ->
                result += "x[$index] = $value\n"
            }

            binding.resultTv.text = result
        }
    }

    private fun getSequence(): DoubleArray {
        val originalSequence = binding.sequenceEditText.text.toString()
        val numbers = originalSequence.split("").filter { it.isNotBlank() }
            .mapNotNull { it.toDoubleOrNull() }
            .toDoubleArray()

        return numbers
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}