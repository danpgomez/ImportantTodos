package com.example.importanttodos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.importanttodos.databinding.FragmentTodosBinding

class TodosFragment : Fragment() {

    private var _binding: FragmentTodosBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTodosBinding.inflate(inflater, container, false)

        val application = requireNotNull(this.activity).application
        val dao = TodosDatabase.getInstance(application).todosDao
        val viewModelFactory = TodosViewModelFactory(dao)
        val viewModel = ViewModelProvider(this, viewModelFactory)[TodosViewModel::class.java]
        val adapter = TodoItemAdapter()
        binding.viewModel = viewModel
        binding.todosList.adapter = adapter
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.todos.observe(viewLifecycleOwner) { todosList ->
            todosList?.let {
                adapter.data = it
            }
        }

        return binding.root
    }
}
