package com.example.todolist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class ToDoItemsAdapter(private val todoItemsList:ArrayList<TodoItem>):
    RecyclerView.Adapter<ToDoItemsAdapter.ViewHolder>() {

    // val e RootLayout din to_do_item_layout
    class ViewHolder (val constraintLayout: ConstraintLayout):RecyclerView.ViewHolder(constraintLayout)

    //Methods we need
    //called when we create a new instance of a ViewHolder; provide extra set ups as soon as the view is created(appearance, functionality)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //we can access each item in the to_do_item_layout with getChild method
        val constraintLayout = LayoutInflater.from(parent.context).inflate(
            R.layout.to_do_item_layout, parent, false) as ConstraintLayout

        return ViewHolder(constraintLayout)
    }

    //bind the data we find in the todoItemsList to each of the views, so that we create a custom view for each of the items
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val constraintLayout = holder.constraintLayout  //holder. the val we passed into the class ViewHolder
        val nameTextView = constraintLayout.getChildAt(0) as TextView
        val priorityTextView = constraintLayout.getChildAt(1) as TextView
        nameTextView.text = todoItemsList[position].name
        priorityTextView.text = if (todoItemsList[position].isPriority) "!!" else ""
    }

    //return how many items we have in our list
    override fun getItemCount(): Int {
        return todoItemsList.size
    }
}