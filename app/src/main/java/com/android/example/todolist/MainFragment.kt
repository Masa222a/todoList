package com.android.example.todolist

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.example.todolist.databinding.FragmentMainBinding
import org.json.JSONArray

class MainFragment : Fragment() {
    lateinit var binding: FragmentMainBinding
    lateinit var adapter: TodoListAdapter
    var todoList = mutableListOf<Todo>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root
        val pref = PreferenceManager.getDefaultSharedPreferences(requireActivity())
        pref.edit{
            clear()
        }
        val recyclerView = binding.recyclerView
        val layoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.layoutManager = layoutManager
        adapter = TodoListAdapter(todoList)
        recyclerView.adapter = adapter

        binding.addButton.setOnClickListener {
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.container, AddTodoFragment())
                addToBackStack(null)
                commit()
            }
        }
        return view
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()
        loadTodo("todo")
        adapter.notifyDataSetChanged()
    }

    fun loadTodo(key: String) {
        val pref = requireActivity().getSharedPreferences("pref", Context.MODE_PRIVATE)
        val jsonArray = JSONArray(pref.getString(key, "[]"));

        for (i in 0 until jsonArray.length()) {
            Log.i("loadArrayList", "[$i] -> " + jsonArray.get(i))
        }
    }

}