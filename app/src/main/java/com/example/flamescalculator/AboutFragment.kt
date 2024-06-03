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
import com.example.flamescalculator.databinding.FragmentAboutBinding

/**
 * A simple [Fragment] subclass.
 * Use the [AboutFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AboutFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val binding: FragmentAboutBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_about, container, false)

        (activity as MainActivity?)?.setActionBarTitle("About")

        val textView: TextView = binding.aboutText
        val paint = textView.paint
        val width = paint.measureText(textView.text.toString())

        val shader = LinearGradient(
            0f, 0f, width, textView.textSize, intArrayOf(
                Color.parseColor("#FF61D2"),
                Color.parseColor("#FE9090"),
            ), null, Shader.TileMode.CLAMP
        )
        textView.paint.shader = shader

        return binding.root
   }
}