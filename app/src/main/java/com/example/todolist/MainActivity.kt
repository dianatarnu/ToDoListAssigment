package com.example.todolist

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var todoItemRecyclerView: RecyclerView
    private lateinit var recyclerAdapter: ToDoItemsAdapter
    private lateinit var recyclerLayoutManager: RecyclerView.LayoutManager

    var todoItemsList = ArrayList<TodoItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        todoItemsList.add(TodoItem("Buy groceries"))
        todoItemsList.add(TodoItem("Do laundry", true))
        todoItemsList.add(TodoItem("Play guitar", false))

        todoItemRecyclerView = findViewById(R.id.todo_item_recycler_view)
        recyclerLayoutManager = LinearLayoutManager(this)
        recyclerAdapter = ToDoItemsAdapter(todoItemsList, this)

        todoItemRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = recyclerLayoutManager
            adapter = recyclerAdapter
        }
    }

    //switch between the pages with onclick listener
    public fun navToAddItemAction(view: View){
        // An intent is an abstract description of an operation to be performed.
        // Its most significant use is in the launching of activities, where it can be thought of as the glue between activities
        val intent: Intent = Intent(this, AddItemActivity::class.java)
        startActivity(intent)
    }
}