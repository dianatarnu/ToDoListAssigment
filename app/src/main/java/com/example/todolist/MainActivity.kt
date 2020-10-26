package com.example.todolist

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var todoItemRecyclerView: RecyclerView
    private lateinit var recyclerAdapter: ToDoItemsAdapter
    private lateinit var recyclerLayoutManager: RecyclerView.LayoutManager
    private lateinit var todaysItemsButton: Button
    private lateinit var pastItemsButton: Button

    var todoItemsList = ArrayList<TodoItem>()
    var todaysItemsList = ArrayList<TodoItem>()
    var pastItemsList = ArrayList<TodoItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //create a Database instance
        val dbo = DatabaseOperations(this)
        val cursor = dbo.getAllItems(dbo)       //resemble with tables - columns & rows
        // store the results
        with(cursor){
            while (moveToNext()){
                val itemName = getString(getColumnIndex(DatabaseInfo.TableInfo.COLUMN_ITEM_NAME))
                val itemPriority = getInt((getColumnIndex(DatabaseInfo.TableInfo.COLUMN_ITEM_PRIORITY)))
                val itemDate = getString((getColumnIndex(DatabaseInfo.TableInfo.COLUMN_ITEM_DATE)))
                val isUrgent = if(itemPriority == 0) false else true

                val newTodoItem = TodoItem(itemName, isUrgent)
                newTodoItem.dateString = itemDate
                todoItemsList.add(newTodoItem)

                if(itemDate == getDateAsString()){
                    todaysItemsList.add(newTodoItem)
                }
                else{
                    pastItemsList.add(newTodoItem)
                }
            }
        }

//        todoItemsList.add(TodoItem("Buy groceries"))
//        todoItemsList.add(TodoItem("Do laundry", true))
//        todoItemsList.add(TodoItem("Play guitar", false))

        todoItemRecyclerView = findViewById(R.id.todo_item_recycler_view)
        todaysItemsButton = findViewById(R.id.todays_items_button)
        pastItemsButton = findViewById(R.id.past_item_button)

        recyclerLayoutManager = LinearLayoutManager(this)
        recyclerAdapter = ToDoItemsAdapter(todoItemsList, this)

        todoItemRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = recyclerLayoutManager
            adapter = recyclerAdapter
        }
    }

    public fun displayTodaysItems(view: View){
        recyclerAdapter = ToDoItemsAdapter(todaysItemsList, this)

        todoItemRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = recyclerLayoutManager
            adapter = recyclerAdapter
        }

        todaysItemsButton.background = getDrawable(R.color.pureBlack)
        todaysItemsButton.setTextColor(getColor(R.color.pureWhite))
        pastItemsButton.background = getDrawable(R.color.pureWhite)
        pastItemsButton.setTextColor(getColor(R.color.pureBlack))
    }

    public fun displayPastItems(view: View){
        recyclerAdapter = ToDoItemsAdapter(pastItemsList, this)

        todoItemRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = recyclerLayoutManager
            adapter = recyclerAdapter
        }

        todaysItemsButton.background = getDrawable(R.color.pureWhite)
        todaysItemsButton.setTextColor(getColor(R.color.pureBlack))
        pastItemsButton.background = getDrawable(R.color.pureBlack)
        pastItemsButton.setTextColor(getColor(R.color.pureWhite))
    }

    //switch between the pages with onclick listener
    public fun navToAddItemAction(view: View){
        // An intent is an abstract description of an operation to be performed.
        // Its most significant use is in the launching of activities, where it can be thought of as the glue between activities
        val intent: Intent = Intent(this, AddItemActivity::class.java)
        startActivity(intent)
    }

    fun getDateAsString():String{
        val date = Calendar.getInstance()
        val year = date.get(Calendar.YEAR).toString()
        val month = date.get(Calendar.MONTH).toString()
        val day = date.get(Calendar.DAY_OF_MONTH).toString()
        return "$year/$month/$day"
    }
}