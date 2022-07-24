package com.android.example.todolist

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import com.android.example.todolist.databinding.FragmentAddTodoBinding

class AddTodoFragment : Fragment() {
    lateinit var binding: FragmentAddTodoBinding

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
                val pref = this.requireActivity().getSharedPreferences("pref", Context.MODE_PRIVATE)
                pref.edit {
                    putString("title", binding.editTitle.text.toString())
                    putString("date", binding.editDate.text.toString())
                    apply()
                }
                binding.editTitle.text.clear()
                binding.editDate.text.clear()
            }
        }

        binding.backButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        return view
    }

}