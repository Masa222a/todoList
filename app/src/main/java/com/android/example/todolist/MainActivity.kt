package com.android.example.todolist

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.example.todolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var adapter: TodoListAdapter
    lateinit var todoList: MutableList<Todo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val view = binding.root

        val list1 = Todo("テストタイトル1", "2022/07/21")
        val list2 = Todo("テストタイトル2", "2022/07/22")
        val list3 = Todo("テストタイトル3", "2022/07/23")
        val list4 = Todo("テストタイトル4", "2022/07/24")
        todoList = mutableListOf(list1, list2,list3,list4)

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        adapter = TodoListAdapter(todoList)
        recyclerView.adapter = adapter

        binding.addButton.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.container, AddTodoFragment())
                addToBackStack(null)
                commit()
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()
        getTodo()
        adapter.notifyDataSetChanged()
    }

    fun getTodo() {
        val pref = getSharedPreferences("pref", Context.MODE_PRIVATE)
        val title = pref.getString("title", "")
        val date = pref.getString("date", "")
        if (title != null && date != null) {
            var todo = Todo(title, date)
            todoList.add(todo)
            Log.d("todoMainActivity", "${todo}")
        }
    }
}