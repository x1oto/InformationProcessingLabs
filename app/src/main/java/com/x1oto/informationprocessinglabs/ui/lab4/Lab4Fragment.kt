package com.x1oto.informationprocessinglabs.ui.lab4

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.x1oto.informationprocessinglabs.databinding.FragmentLab4Binding
import com.x1oto.informationprocessinglabs.ui.lab3.Lab3ViewModel


class Lab4Fragment : Fragment() {

    private var _binding: FragmentLab4Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val lab4ViewModel =
            ViewModelProvider(this).get(Lab4ViewModel::class.java)
        _binding = FragmentLab4Binding.inflate(inflater, container, false)
        val root: View = binding.root

        setupCalculateButton(lab4ViewModel)
        return root
    }

    private fun setupCalculateButton(viewModel: Lab4ViewModel) {
        binding.calculateBt.setOnClickListener {
            val x = getSequence()
            var result = ""

            val directDFT = viewModel.calculateDFT(x)
            result += "Пряме ШПФ:\n"
            directDFT.forEachIndexed { index, value ->
                result += "X[$index] = ${value.real}\n"
            }

            val inverseDFT = viewModel.calculateInverseDFT(directDFT)
            result +="\nОбернене ШПФ:\n"
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
