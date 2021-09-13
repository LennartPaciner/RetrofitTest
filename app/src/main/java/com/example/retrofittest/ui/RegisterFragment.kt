package com.example.retrofittest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.retrofittest.BaseApplication
import com.example.retrofittest.R
import com.example.retrofittest.database_tables.Passwords
import com.example.retrofittest.database_tables.Usernames
import com.example.retrofittest.databinding.FragmentRegisterBinding
import com.example.retrofittest.viewmodels.DatabaseViewModel
import com.example.retrofittest.viewmodels.DatabaseViewModelFactory
import kotlinx.coroutines.launch

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding ?= null
    private val binding get() = _binding!!

    private val viewModelDatabase: DatabaseViewModel by viewModels {
        DatabaseViewModelFactory((activity?.application as BaseApplication).databaseRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.buttonRegister.setOnClickListener {
            if (binding.registerName.text != null && binding.registerPassword.text != null) {
                val username_new = Usernames(binding.registerName.text.toString())
                val password_new = Passwords(binding.registerPassword.text.toString())

                // ignoriere dass es den schon geben könnte - vllt noch adden TODO
                viewModelDatabase.insertUsernames(username_new)
                viewModelDatabase.insertPasswords(password_new)
            }
        }

        binding.buttonRegisterBack.setOnClickListener {
            val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
            findNavController().navigate(action)
        }

        binding.buttonRegisterDelete.setOnClickListener {
            lifecycleScope.launch {
                viewModelDatabase.deleteUsernamesTable()
                Toast.makeText(requireContext(), "Deleted", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


























