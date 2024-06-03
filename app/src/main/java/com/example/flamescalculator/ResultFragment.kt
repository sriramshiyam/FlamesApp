package com.example.flamescalculator

import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.flamescalculator.databinding.ActivityMainBinding
import com.example.flamescalculator.databinding.FragmentResultBinding

/**
 * A simple [Fragment] subclass.
 * Use the [ResultFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ResultFragment : Fragment() {

    private lateinit var viewModel: FlamesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentResultBinding>(
            inflater,
            R.layout.fragment_result,
            container,
            false
        )


        (activity as MainActivity?)?.setActionBarTitle("Result")

        viewModel = ViewModelProvider(this)[FlamesViewModel::class.java]

        val args = ResultFragmentArgs.fromBundle(requireArguments())

        binding.flamesViewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.calculate(args.name1, args.name2)

        val textView: TextView = binding.resultText
        val paint = textView.paint
        val width = paint.measureText(textView.text.toString())

        val shader = LinearGradient(
            0f, 0f, width, textView.textSize, intArrayOf(
                Color.parseColor("#FF61D2"),
                Color.parseColor("#FE9090"),
            ), null, Shader.TileMode.CLAMP
        )
        textView.paint.shader = shader
        // Inflate the layout for this fragment
        return binding.root
    }


}