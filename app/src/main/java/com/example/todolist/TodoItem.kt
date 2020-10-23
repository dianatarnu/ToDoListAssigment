package com.example.todolist

import java.util.*

class TodoItem (var name: String){
    var isPriority = false
    var date = Calendar.getInstance()

    constructor(name: String, isPriority: Boolean) :this(name){
        this.isPriority = isPriority
    }

    fun getDateAsString():String{
        val year = date.get(Calendar.YEAR).toString()
        val month = date.get(Calendar.MONTH).toString()
        val day = date.get(Calendar.DAY_OF_MONTH).toString()
        return "$year/$month/$day"
    }
}