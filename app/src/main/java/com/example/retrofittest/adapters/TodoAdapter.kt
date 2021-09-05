package com.example.retrofittest.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofittest.api.Todo
import com.example.retrofittest.databinding.ItemTodoBinding

//strg+i um benötigte funktionen zu implementieren
class TodoAdapter : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>(){

    // benötigt damit recyclerview bei veränderung nicht komplette view neu lädt sondern nur verändertes item
    private val diffCallback = object : DiffUtil.ItemCallback<Todo>() {
        override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem.id == newItem.id
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    //unsere items für die recyclerliste
    var todos: List<Todo>
        get() = differ.currentList
        set(value) {differ.submitList(value)}


    //braucht innere klasse um die views für den recycler view zu haben (für so ein item ein layout file anlegen)
    //im konstruktor: die view zu dem item übergeben für die liste -> hier mittels view binding
    inner class TodoViewHolder(val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root)

    //wird aufgerufen wenn user zb scrolled und neues item benötigt wird - mittels view binding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(ItemTodoBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))
    }

    //data zu unseren items zu übergeben
    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.binding.apply {
            tvTitle.text = todos[position].title
            cbDone.isChecked = todos[position].completed
        }
    }


    //wieviele items wir im recyclerview haben
    override fun getItemCount(): Int {
        return todos.size
    }

    fun setTodoList(todo: List<Todo>) {
        todos = todo.toMutableList()
    }
}

/*
class TodoAdapter : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    //mittels view binding
    inner class TodoViewHolder(val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<Todo>() {
        override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem.id == newItem.id
        }

    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var todos: List<Todo>
        get() = differ.currentList
        set(value) {differ.submitList(value)}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(ItemTodoBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.binding.apply {
            val todo = todos[position]
            tvTitle.text = todo.title
            cbDone.isChecked = todo.completed
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }

    fun setTodoList(todo: List<Todo>) {
        this.todos = todo.toMutableList()
        notifyDataSetChanged()
    }
}
 */