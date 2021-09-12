package com.example.retrofittest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.retrofittest.BaseApplication
import com.example.retrofittest.R
import com.example.retrofittest.databinding.FragmentStartBinding
import com.example.retrofittest.viewmodels.DatabaseViewModel
import com.example.retrofittest.viewmodels.DatabaseViewModelFactory
import kotlinx.android.synthetic.main.fragment_start.*

//Fragment mittels View Binding
class StartFragment : Fragment() {

    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!

    private val viewModelDatabase: DatabaseViewModel by viewModels {
        DatabaseViewModelFactory((activity?.application as BaseApplication).databaseRepository)
    }

    private val args: StartFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentStartBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (args.username != "null" && args.password != "null"){
            Toast.makeText(requireContext(), "Username: " + args.username + "\nPassword: " + args.password, Toast.LENGTH_SHORT).show()
        }

        viewModelDatabase.getAllUsernames().observe(viewLifecycleOwner, {
            binding.startUsername.text = it[0].toString()
            binding.startPassword.text = it[0].toString()
        })


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}