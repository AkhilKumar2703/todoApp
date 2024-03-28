package com.techmania.todolist

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    lateinit var item :EditText
    lateinit var add :Button
    lateinit var listView: ListView
    var itemList = ArrayList<String>()
    var fileHelper =FileHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        item = findViewById(R.id.editText)
        add = findViewById(R.id.button)
        listView = findViewById(R.id.list)
        itemList = fileHelper.readData(this)
        var arrayAdapter = ArrayAdapter(this,R.layout.color_layout,R.id.textcolour,itemList)
        listView.adapter = arrayAdapter


        add.setOnClickListener {

            var itemName : String = item.text.toString()
            itemList.add(itemName)
            item.setText("")
            fileHelper.writeData(itemList,applicationContext)
            arrayAdapter.notifyDataSetChanged()

        }
        listView.setOnItemClickListener { adapterView, view, position, l ->
            var alert = AlertDialog.Builder(this)
            alert.setTitle("DELETE")
            alert.setMessage("Do you really want to delete this item from the list ? Hope you have done that. ")
            alert.setCancelable(false)
            alert.setNegativeButton("No", DialogInterface.OnClickListener { DailogInterface, i ->
                DailogInterface.cancel()
            })
            alert.setPositiveButton("Yes", DialogInterface.OnClickListener { DailogInterface, i ->
                itemList.removeAt(position)
                arrayAdapter.notifyDataSetChanged()
                fileHelper.writeData(itemList,applicationContext)


            })
            alert.create().show()
        }

    }
}