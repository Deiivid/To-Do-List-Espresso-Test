package es.deiividdev.com.to_do_list_espresso_test.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.deiividdev.com.to_do_list_espresso_test.databinding.ItemTaskBinding
import es.deiividdev.com.to_do_list_espresso_test.model.Task

class TaskAdapter(private val taskList: MutableList<Task>) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    inner class TaskViewHolder(val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = taskList[position]
        holder.binding.apply {
            taskName.text = task.name
            taskCheckbox.isChecked = task.isCompleted
            taskCheckbox.setOnCheckedChangeListener { _, isChecked ->
                task.isCompleted = isChecked
            }
            deleteButton.setOnClickListener {
                taskList.removeAt(position)
                notifyItemRemoved(position)
                notifyItemRangeChanged(position, taskList.size)
            }
        }
    }

    override fun getItemCount(): Int = taskList.size
}
