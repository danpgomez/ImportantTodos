package com.example.importanttodos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.importanttodos.databinding.FragmentEditTodoBinding
import com.example.importanttodos.databinding.FragmentTodosBinding

class EditTodoFragment : Fragment() {
    private var _binding: FragmentEditTodoBinding? = null
    private val binding get() = _binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditTodoBinding.inflate(inflater, container, false)
        val view = binding?.root

        val todoId = EditTodoFragmentArgs.fromBundle(requireArguments()).todoId
        val application = requireNotNull(this.activity).application
        val dao = TodosDatabase.getInstance(application).todosDao
        val viewModelFactory = EditTodoViewModelFactory(todoId, dao)
        val viewModel = ViewModelProvider(this, viewModelFactory)[EditTodoViewModel::class.java]

        binding?.viewModel = viewModel
        binding?.lifecycleOwner = viewLifecycleOwner

        viewModel.navigateToList.observe(viewLifecycleOwner) { shouldNavigate ->
            if (shouldNavigate) {
                val action = EditTodoFragmentDirections.actionEditTodoFragmentToTodosFragment()
                view?.findNavController()?.navigate(action)
                viewModel.onNavigatedToList()
            }
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
