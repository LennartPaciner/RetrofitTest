package com.example.retrofittest.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.retrofittest.CreateTodo
import com.example.retrofittest.R
import com.example.retrofittest.RetrofitInstance
import com.example.retrofittest.databinding.FragmentPostBinding
import com.google.gson.Gson
import retrofit2.HttpException

class PostFragment : Fragment() {

    private var _binding: FragmentPostBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPostBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonPost.setOnClickListener {
            // Hide Soft Keyboard !
            val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(requireView().getWindowToken(), 0)

            createTodo()
        }

        binding.buttonUpdate.setOnClickListener {
            // Hide Soft Keyboard !
            val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(requireView().getWindowToken(), 0)

            updateTodo()
        }

        binding.buttonDelete.setOnClickListener {
            // Hide Soft Keyboard !
            val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(requireView().getWindowToken(), 0)

            deleteTodo()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun createTodo() {
        if (!binding.editPost1.text.isEmpty() && !binding.editPost2.text.isEmpty() && !binding.editPost3.text.isEmpty()){
            val todo = CreateTodo(1, binding.editPost3.text.toString(), binding.editPost2.text.toString(), binding.editPost1.text.toString().toInt())

            lifecycleScope.launchWhenCreated {
                val response = try {
                    RetrofitInstance.api.createTodo(todo)
                } catch (e: HttpException) {
                    return@launchWhenCreated
                }

                if (response.isSuccessful && response.body() != null) {

                    Log.d("jo", "${response.body()}")
                    Log.d("jo", Gson().toJson(response.body()))
                    Log.d("jo", "${response.code()}")

                    binding.textViewPost.text = Gson().toJson(response.body())

                    Toast.makeText(requireContext(), response.code().toString(),Toast.LENGTH_LONG).show()
                } else {

                    Log.e("jo", "response not succesful")
                }
            }
        }
    }

    private fun updateTodo() {
        val todo = CreateTodo(1,"text", "okay", 99)

        lifecycleScope.launchWhenCreated {
            val response = try {
                RetrofitInstance.api.putTodo(42, todo)
            } catch (e: HttpException) {
                return@launchWhenCreated
            }

            if (response.isSuccessful && response.body() != null) {

                Log.d("jo", "${response.body()}")
                Log.d("jo", Gson().toJson(response.body()))
                Log.d("jo", "${response.code()}")

                binding.textViewPost.text = Gson().toJson(response.body())

                Toast.makeText(requireContext(), response.code().toString(),Toast.LENGTH_LONG).show()
            } else {

                Log.e("jo", "response not succesful")
            }
        }
    }

    private fun deleteTodo() {
        lifecycleScope.launchWhenCreated {
            val response = try {
                RetrofitInstance.api.deleteTodo(1)
            } catch (e: HttpException){
                return@launchWhenCreated
            }

            Toast.makeText(requireContext(), response.code().toString(),Toast.LENGTH_LONG).show()
        }
    }
}



















