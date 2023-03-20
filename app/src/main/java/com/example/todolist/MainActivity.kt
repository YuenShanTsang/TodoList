package com.example.todolist

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.todolist.databinding.ActivityMainBinding
import com.example.todolist.databinding.TodoBinding

class TodoAdapter(context: Context, tasks: List<String>) :
    ArrayAdapter<String>(context, R.layout.todo, R.id.task_text_view, tasks) {

    private val inflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding: TodoBinding
        val view = convertView ?: inflater.inflate(R.layout.todo, parent, false)
        binding = TodoBinding.bind(view)

        binding.taskTextView.text = getItem(position)

        binding.removeButton.setOnClickListener {
            remove(getItem(position))
        }

        return view
    }
}
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: TodoAdapter

    private var tasks = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = TodoAdapter(this, tasks)
        binding.todoListView.adapter = adapter

        binding.addButton.setOnClickListener {
            val task = binding.inputEditText.text.toString().trim()
            if (task.isNotEmpty()) {
                tasks.add(task)
                adapter.notifyDataSetChanged()
                binding.inputEditText.text.clear()
            }
        }
    }
}


