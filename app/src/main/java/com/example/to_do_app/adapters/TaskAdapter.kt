package com.example.to_do_app.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.to_do_app.data.Task
import com.example.to_do_app.databinding.ItemTaskBinding
import com.example.to_do_app.utils.addStrikethrough

class TaskAdapter(
var items: List<Task>,
val onClick: (Int) -> Unit,
val onDelete: (Int) -> Unit,
val onCheck: (Int) -> Unit
) : Adapter<TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = items[position]
        holder.render(task)
        holder.itemView.setOnClickListener {
            onClick(position)
        }
        holder.binding.deleteButton.setOnClickListener {
            onDelete(position)
        }
        holder.binding.doneCheckBox.setOnCheckedChangeListener { _, _ ->
            if (holder.binding.doneCheckBox.isPressed) {
                onCheck(position)
            }
        }
    }

    fun updateItems(items: List<Task>) {
        this.items = items
        notifyDataSetChanged()
    }
}

class TaskViewHolder(val binding: ItemTaskBinding) : ViewHolder(binding.root) {

    fun render(task: Task) {
        if (task.done) {
            binding.titleTextView.text = task.title.addStrikethrough()
        } else {
            binding.titleTextView.text = task.title
        }
        binding.doneCheckBox.isChecked = task.done
    }
}