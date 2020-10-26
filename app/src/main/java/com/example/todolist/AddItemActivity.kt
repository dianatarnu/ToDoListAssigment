package com.example.todolist

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AddItemActivity : AppCompatActivity() {
    private lateinit var itemNameEditTextView: TextView
    private lateinit var priorityCheckBox: CheckBox
    private lateinit var titleTextView: TextView

    //see if we have to create new item
    private var isNewItem = true
    private lateinit var oldItem: TodoItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)

        itemNameEditTextView = findViewById(R.id.enter_item)
        priorityCheckBox = findViewById(R.id.priority_checkbox)
        titleTextView = findViewById(R.id.welcome)

        val itemName = intent.getStringExtra("ITEM_NAME")
        val itemPriority = intent.getBooleanExtra("ITEM_PRIORITY", false)

        if(itemName != null){
            itemNameEditTextView.setText(itemName)
            titleTextView.setText(R.string.edit_item)

            oldItem = TodoItem(itemName)
            isNewItem = false
        }
        if(itemPriority == true){
            priorityCheckBox.isChecked = true

            oldItem.isPriority = itemPriority
        }
    }

    public fun saveItemAction(view: View){
        val itemName = itemNameEditTextView.text.toString()
        val itemPriority = priorityCheckBox.isChecked
        val newTodoItem = TodoItem(itemName, itemPriority)

        val dbo = DatabaseOperations(this)
        if(isNewItem){
            dbo.addItem(dbo, newTodoItem)
        }
        else{
            dbo.updateItem(dbo, this.oldItem, newTodoItem)
        }
        //cancel automatically after saving
        val intent: Intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    public fun cancelAction(view: View){
        val intent: Intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}