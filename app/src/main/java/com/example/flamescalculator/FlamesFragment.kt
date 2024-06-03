package com.example.flamescalculator

import android.os.Bundle
import android.text.Editable
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.flamescalculator.databinding.FragmentFlamesBinding

/**
 * A simple [Fragment] subclass.
 * Use the [FlamesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FlamesFragment : Fragment() {

    lateinit var binding: FragmentFlamesBinding

    override fun onResume() {
        super.onResume()
        // Set title bar
        (activity as MainActivity)
            .setActionBarTitle("Flames Calculator")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_flames, container, false)

        binding.name1.setText(savedInstanceState?.getString("name1") ?: "")
        binding.name2.setText(savedInstanceState?.getString("name2") ?: "")

        binding.calculate.setOnClickListener {
            calculate()
        }
        setHasOptionsMenu(true)
        return binding.root
    }


    fun calculate() {

        binding.apply {
            name1.text = name1.text.trim() as Editable?
            name2.text = name2.text.trim() as Editable?

            if (name1.text.isEmpty() || name2.text.isEmpty()) {
                Toast.makeText(context, "Enter the name...", Toast.LENGTH_SHORT).show()
            } else if (name1.text.contains(" ") || name2.text.contains(" ")) {
                Toast.makeText(context, "No spaces allowed in between...", Toast.LENGTH_SHORT)
                    .show()
            } else {
                requireView().findNavController()
                    .navigate(FlamesFragmentDirections.actionFlamesFragmentToResultFragment(name1.text.toString(), name2.text.toString()))
            }
        }

    }



    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        requireView().findNavController()
            .navigate(FlamesFragmentDirections.actionFlamesFragmentToAboutFragment())
        return true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("name1", binding.name1.text.toString())
        outState.putString("name2", binding.name2.text.toString())
    }
}