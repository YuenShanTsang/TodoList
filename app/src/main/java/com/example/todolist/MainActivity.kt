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


class MainActivity : AppCompatActivity() {
    // View binding
    private lateinit var binding: ActivityMainBinding
    // ListView adapter
    private lateinit var adapter: TodoAdapter

    // A mutable list to store the tasks to display in the ListView
    private var tasks = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Setup view binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize the adapter
        adapter = TodoAdapter(this, tasks)
        // Set the adapter for the ListView
        binding.todoListView.adapter = adapter

        // To add a new task to the list
        binding.addButton.setOnClickListener {
            // To get the input from user and remove leading or trailing space
            val task = binding.inputEditText.text.toString().trim()
            // Check if the string is not empty before adding to the list
            if (task.isNotEmpty()) {
                tasks.add(task)
                // To update the data
                adapter.notifyDataSetChanged()
                // EditText is cleared when the item is added
                binding.inputEditText.text.clear()
            }
        }
    }
}

// Adapter for the items in the list view
class TodoAdapter(context: Context, tasks: List<String>) : ArrayAdapter<String>(context, R.layout.todo, R.id.task_text_view, tasks) {

    // To inflate the layout for each item in the list
    private val inflater = LayoutInflater.from(context)

    // To call for each item in the list and get its position
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // View binding
        val binding: TodoBinding

        // To inflate the view if it is null, or reuse the existing view if it is not null
        val view = convertView ?: inflater.inflate(R.layout.todo, parent, false)

        // To bind the views in the layout
        binding = TodoBinding.bind(view)

        // To display the item at the current position in the list
        binding.taskTextView.text = getItem(position)

        // To remove the item at the current position from the list
        binding.removeButton.setOnClickListener {
            remove(getItem(position))
        }

        // To return the item at the current position
        return view
    }
}
