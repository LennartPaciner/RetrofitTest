package com.example.retrofittest.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.retrofittest.BaseApplication
import com.example.retrofittest.api.CreateTodo
import com.example.retrofittest.api.TodoApi
import com.example.retrofittest.database_tables.Passwords
import com.example.retrofittest.database_tables.Usernames
import com.example.retrofittest.databinding.FragmentPostBinding
import com.example.retrofittest.repository.TodoRepository
import com.example.retrofittest.viewmodels.DatabaseViewModel
import com.example.retrofittest.viewmodels.DatabaseViewModelFactory
import com.example.retrofittest.viewmodels.TodoViewModel
import com.example.retrofittest.viewmodels.TodoViewModelFactory

class PostFragment : Fragment() {

    private var _binding: FragmentPostBinding? = null
    private val binding get() = _binding!!

    //viewmodel
    private lateinit var viewModel: TodoViewModel

    private val viewModel2: DatabaseViewModel by viewModels {
        DatabaseViewModelFactory((activity?.application as BaseApplication).databaseRepository)
    }

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

        val todoApi = TodoApi.getInstance()
        val todoRepository = TodoRepository(todoApi)

        // viewmodel setup
        viewModel = ViewModelProvider(this, TodoViewModelFactory(todoRepository)).get(TodoViewModel::class.java)

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
            /*
            //überprüft ob was an tododelete verändert wurde -> wenn response successful war wird es gesetzt
            viewModel.todoDelete.observe(viewLifecycleOwner, {
                Toast.makeText(requireContext(), it.code().toString(),Toast.LENGTH_SHORT).show()
            })
            viewModel.deleteTodo(1)

             */
            viewModel2.getAllUsernames().observe(viewLifecycleOwner, {
                Toast.makeText(requireContext(), it.toString(),Toast.LENGTH_SHORT).show()
            })

            val username = Usernames("Balu")
            viewModel2.insertUsernames(username)

            val password = Passwords("123")
            viewModel2.insertPasswords(password)


        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun createTodo() {
        if (!binding.editPost1.text.isEmpty() && !binding.editPost2.text.isEmpty() && !binding.editPost3.text.isEmpty()){
            val todo = CreateTodo(1, binding.editPost3.text.toString(), binding.editPost2.text.toString(), binding.editPost1.text.toString().toInt())

            viewModel.todoCreate.observe(viewLifecycleOwner, {
                binding.textViewPost.text = it
            })

            viewModel.createTodo(todo)
        }
    }

    private fun updateTodo() {
        val todo = CreateTodo(1,"text", "okay", 99)

        viewModel.todoUpdate.observe(viewLifecycleOwner, {
            binding.textViewPost.text = it
        })

        viewModel.updateTodo(42, todo)

    }

}



















