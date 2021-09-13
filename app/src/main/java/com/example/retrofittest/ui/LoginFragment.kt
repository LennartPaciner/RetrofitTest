package com.example.retrofittest.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.retrofittest.BaseApplication
import com.example.retrofittest.databinding.FragmentLoginBinding
import com.example.retrofittest.databinding.FragmentStartBinding
import com.example.retrofittest.viewmodels.DatabaseViewModel
import com.example.retrofittest.viewmodels.DatabaseViewModelFactory
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.coroutines.launch


// TODO sieh viewmodel ob fun oder suspend fun da mehr sinn macht
// TODO sql abfrage so dass er nur eins returned und keine liste -> wsh SELECT username .. statt SELECT *
// TODO hier noch comments adden und bissl aufräumen mit benennung
// TODO couroutines?
// TODO nochmal allgemein projekt durchgehen und schauen obs fertig ist


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModelDatabase: DatabaseViewModel by viewModels {
        DatabaseViewModelFactory((activity?.application as BaseApplication).databaseRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonLogin.setOnClickListener {
            if (binding.editName.text != null && binding.editPassword.text != null) {
                val username = edit_name.text.toString()
                val password = edit_password.text.toString()

                var resultUsername = false
                var resultPassword = false

                lifecycleScope.launch {

                    val checkUserName = viewModelDatabase.checkRegisterUsername(username)
                    Log.d("bojack", checkUserName.toString())
                    for (name in checkUserName) {
                        if (name.username == username) {
                            resultUsername = true
                        }
                    }


                    val ergebnis = viewModelDatabase.checkRegisterPassword(password)
                    for (pw in ergebnis) {
                        if (pw.password == password) {
                            resultPassword = true
                        }
                    }

                    Log.d("XD", "resultpassword scope" + resultPassword.toString())



                    if (resultUsername && resultPassword) {
                        edit_name.text.clear()
                        edit_password.text.clear()
                        val action = LoginFragmentDirections.actionLoginFragmentToStartFragment(username, password)
                        findNavController().navigate(action)
                    }
                    else {
                        Log.d("XD", resultUsername.toString())
                        Toast.makeText(requireContext(), "Name oder Passwort falsch", Toast.LENGTH_SHORT).show()
                    }

                }

            }
        }

        binding.buttonRegister.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}





















