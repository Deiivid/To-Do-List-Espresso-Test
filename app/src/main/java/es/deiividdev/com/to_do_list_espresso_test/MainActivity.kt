package es.deiividdev.com.to_do_list_espresso_test

import android.content.Intent
import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import es.deiividdev.com.to_do_list_espresso_test.adapter.TaskAdapter
import es.deiividdev.com.to_do_list_espresso_test.databinding.ActivityMainBinding
import es.deiividdev.com.to_do_list_espresso_test.model.Task
//test jenkins
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val taskList = mutableListOf<Task>()
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        taskAdapter = TaskAdapter(taskList)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = taskAdapter
        }

        //comment this just for testing proposes
        // Add 100 tasks to the list for testing
       /*
      for (i in 1..100) {
            taskList.add(Task("Task $i"))
        }*/
        taskAdapter.notifyDataSetChanged()

        binding.addButton.setOnClickListener {
            val taskName = binding.inputTask.text.toString()
            if (taskName.isNotEmpty()) {
                taskList.add(Task(taskName))
                taskAdapter.notifyItemInserted(taskList.size - 1)
                binding.inputTask.text.clear()
            }
        }

        binding.navigateButton.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
    }
}
