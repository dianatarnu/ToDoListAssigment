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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)

        itemNameEditTextView = findViewById(R.id.enter_item)
        priorityCheckBox = findViewById(R.id.priority_checkbox)
        titleTextView = findViewById(R.id.welcome)

        val itemName = intent.getStringExtra("ITEM_NAME")
        val itemPriority = intent.getBooleanExtra("ITEM_PRIORITY", false)

        if(itemName != null){
            itemNameEditTextView.text = itemName
            titleTextView.setText(R.string.edit_item)
        }
        if(itemPriority == true){
            priorityCheckBox.isChecked = true
        }
    }

    public fun saveItemAction(view: View){

    }

    public fun cancelAction(view: View){
        val intent: Intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}