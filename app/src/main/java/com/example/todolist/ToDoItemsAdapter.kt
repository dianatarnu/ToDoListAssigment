package com.example.todolist

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class ToDoItemsAdapter(private val todoItemsList:ArrayList<TodoItem>, val activity: MainActivity):
    RecyclerView.Adapter<ToDoItemsAdapter.ViewHolder>() {

    // val e RootLayout din to_do_item_layout
    class ViewHolder (val constraintLayout: ConstraintLayout):RecyclerView.ViewHolder(constraintLayout)

    //Methods we need
    //called when we create a new instance of a ViewHolder; provide extra set ups as soon as the view is created(appearance, functionality)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //we can access each item in the to_do_item_layout with getChild method
        val constraintLayout = LayoutInflater.from(parent.context).inflate(
            R.layout.to_do_item_layout, parent, false) as ConstraintLayout

        //take to the edit page through click
        constraintLayout.setOnClickListener(View.OnClickListener {
            val nameTextView = constraintLayout.getChildAt(0) as TextView
            val priorityTextView = constraintLayout.getChildAt(1) as TextView
            val nameText = nameTextView.text
            val priorityText = priorityTextView.text
            val hasItemPriority = if(priorityText == "!!") true else false

            val intent: Intent = Intent(parent.context, AddItemActivity::class.java)
            intent.putExtra("ITEM_NAME", nameText)
            intent.putExtra("ITEM_PRIORITY", hasItemPriority)
            activity.startActivity(intent)
        })

        //delete an item through the long click
        constraintLayout.setOnLongClickListener(View.OnLongClickListener{
            val position: Int = parent.indexOfChild(it) //get the position(index) we clicked on the parent

            val todoItemToRemove = activity.todoItemsList[position]     //get the todoitem that we want to remove
            val dbo = DatabaseOperations(parent.context)        //get the new instance of DatabaseOperations
            dbo.deleteItem(dbo, todoItemToRemove)               //delete the item thaht we have selected

            todoItemsList.removeAt(position)   //remove item at that position
            notifyItemRemoved(position)                 //notify the adapter that we removed this child at that position
            true                                        // overrides the short clicklistener
        })

        return ViewHolder(constraintLayout)
    }

    //bind the data we find in the todoItemsList to each of the views, so that we create a custom view for each of the items
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val constraintLayout = holder.constraintLayout  //holder. the val we passed into the class ViewHolder
        val nameTextView = constraintLayout.getChildAt(0) as TextView
        val priorityTextView = constraintLayout.getChildAt(1) as TextView
        val dateTextView = constraintLayout.getChildAt(2) as TextView

        nameTextView.text = todoItemsList[position].name
        priorityTextView.text = if (todoItemsList[position].isPriority) "!!" else ""
        dateTextView.text = todoItemsList[position].dateString
    }

    //return how many items we have in our list
    override fun getItemCount(): Int {
        return todoItemsList.size
    }
}