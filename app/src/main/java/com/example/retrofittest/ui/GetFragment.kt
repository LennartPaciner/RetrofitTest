package com.example.retrofittest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofittest.BaseApplication
import com.example.retrofittest.adapters.TodoAdapter
import com.example.retrofittest.api.TodoApi
import com.example.retrofittest.databinding.FragmentGetBinding
import com.example.retrofittest.repository.TodoRepository
import com.example.retrofittest.viewmodels.TodoViewModel
import com.example.retrofittest.viewmodels.TodoViewModelFactory

// TODO: tutorials hier: https://developer.android.com/courses/kotlin-android-fundamentals/overview
// TODO artikel der mvvm retrofit beispiel beschreibt: https://medium.com/android-beginners/mvvm-with-kotlin-coroutines-and-retrofit-example-d3f5f3b09050
// TODO nochmal cleanes projekt recyclercview raffen, retrofit aufruf, repository aufruf, viewmodel aufruf, in fragment aufrufen

class GetFragment : Fragment() {

    private var _binding: FragmentGetBinding? = null
    private val binding get() = _binding!!

    private lateinit var todoAdapter: TodoAdapter

    //viewmodel
    private val viewModel: TodoViewModel by viewModels {
        TodoViewModelFactory((activity?.application as BaseApplication).retrofitRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGetBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // reyclerviewsetup
        todoAdapter = TodoAdapter()
        binding.rvTodos.adapter = todoAdapter
        binding.rvTodos.layoutManager = LinearLayoutManager(requireContext())

        // viewmodel setup ohne baseapplication
        //viewModel = ViewModelProvider(this, TodoViewModelFactory(todoRepository)).get(TodoViewModel::class.java)

        //this observer will be notified about modifications of the wrapped data only if the paired LifecycleOwner is in active state
        viewModel.todoList.observe(viewLifecycleOwner, {
            todoAdapter.setTodoList(it)
        })

        //nicht sicher ob progressbar was macht -> bekomme todoliste aus viewmodel
        binding.progressBar.isVisible = true
        viewModel.getTodos()
        binding.progressBar.isVisible = false


        /*
        lifecycleScope.launchWhenCreated {
            binding.progressBar.isVisible = true
            val response = try {
                RetrofitInstance.api.getTodos()
            } catch (e: IOException) {
                Log.e("xd", "ioexpection")
                binding.progressBar.isVisible = false
                return@launchWhenCreated
            } catch (e: HttpException) {
                Log.e("xd", "httpexception")
                binding.progressBar.isVisible = false
                return@launchWhenCreated
            }

            if (response.isSuccessful && response.body() != null) {

                todoAdapter.todos = response.body()!!

            } else {

                Log.e("xd", "response not succesful")
            }
            binding.progressBar.isVisible = false
        }*/
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}





















