package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.FragmentFirstBinding
import java.util.*
import kotlin.collections.HashMap


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val unitsMap = HashMap<String, Int>();

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

      _binding = FragmentFirstBinding.inflate(inflater, container, false)



        unitsMap.put("km", 1000 * 100 * 10);
        unitsMap.put("m", 100 * 10);
        unitsMap.put("cm", 10);
        unitsMap.put("mm", 1);

        val spinnerArrayAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
            this.requireContext(), R.layout.support_simple_spinner_dropdown_item, unitsMap.keys.toList()
        )

        binding.fromUnit.adapter = spinnerArrayAdapter
        binding.toUnit.adapter = spinnerArrayAdapter

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.buttonFirst.setOnClickListener {
            var b = Bundle()
            b.putString("s", "${binding.convertInput.text.toString()} ${binding.fromUnit.selectedItem} is ${ binding.convertInput.text.toString().toInt() * unitsMap[binding.fromUnit.selectedItem]!! / unitsMap[binding.toUnit.selectedItem]!!} ${binding.toUnit.selectedItem}")
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, b)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}