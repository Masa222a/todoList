package com.android.example.todolist

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import com.android.example.todolist.databinding.FragmentAddTodoBinding
import com.google.gson.Gson
import org.json.JSONArray

class AddTodoFragment : Fragment() {
    lateinit var binding: FragmentAddTodoBinding
    var todoLists = arrayListOf<Todo>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddTodoBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.registerButton.setOnClickListener {
            if (binding.editTitle.text.isEmpty()) {
                Toast.makeText(activity, "タイトルを入力してください", Toast.LENGTH_SHORT).show()
            } else if (binding.editDate.text.isEmpty()){
                Toast.makeText(activity, "日時を入力してください", Toast.LENGTH_SHORT).show()
            } else {
                todoLists.add(Todo(binding.editTitle.text.toString(), binding.editDate.text.toString()))
                val pref = requireActivity().getSharedPreferences("pref", Context.MODE_PRIVATE)
                val shardPrefEditor = pref.edit()
                val gson = Gson()
                shardPrefEditor.putString("todoLists", gson.toJson(todoLists))
                shardPrefEditor.apply()
                Log.d("Gson", gson.toJson(todoLists))
//                saveArrayList("todo", todoLists)

                binding.editTitle.text.clear()
                binding.editDate.text.clear()
            }
        }

        binding.backButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        return view
    }

//    fun saveArrayList(key: String, arrayList: ArrayList<Todo>) {
//
//        val pref = requireActivity().getSharedPreferences("pref", Context.MODE_PRIVATE)
//        val shardPrefEditor = pref.edit()
//        val gson = Gson()
//
//        val jsonArray = JSONArray(arrayList)
//        shardPrefEditor.putString(key, jsonArray.toString())
//        shardPrefEditor.apply()
//    }
}