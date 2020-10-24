package com.example.todolist

import android.provider.BaseColumns

object DatabaseInfo {
    //query to create the table with 4 columns; called only once
    const val SQL_CREATE_TABLE_QUERY =
        "CREATE TABLE ${TableInfo.TABLE_NAME} (" +
        "${BaseColumns._ID} INTEGER PRIMARY KEY," +
        "${TableInfo.COLUMN_ITEM_NAME} TEXT," +
        "${TableInfo.COLUMN_ITEM_PRIORITY} INTEGER," +      // 0 false, 1 true
        "${TableInfo.COLUMN_ITEM_DATE} TEXT)"

    const val SQL_DELETE_TABLE_QUERY = "DROP TABLE IF EXISTS ${TableInfo.TABLE_NAME}"

    object TableInfo: BaseColumns{
        const val TABLE_NAME = "todoItemsTable"
        const val COLUMN_ITEM_NAME = "itemName"
        const val COLUMN_ITEM_PRIORITY = "itemPriority"
        const val COLUMN_ITEM_DATE = "itemDate"
    }

}