package com.example.firechatapp

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firechatapp.databinding.ActivityChatsBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ChatsActivity : AppCompatActivity() {
    lateinit var database: FirebaseDatabase
    lateinit var binding: ActivityChatsBinding
    lateinit var textsArrayList: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        textsArrayList = arrayListOf()

// Write data to firebase db
        database =
            FirebaseDatabase.getInstance("https://firechat3-99ac4-default-rtdb.firebaseio.com/")
        val dbReference = database.getReference("Chats").child("Sender")

        binding.btnSend.setOnClickListener {
            val message = binding.editedMessage.text.toString()
            dbReference.push().setValue(message)
            binding.editedMessage.setText("")

//  Read data from firebase db
            dbReference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (ds in dataSnapshot.children) {

                        val text = ds.getValue(String::class.java)
                        textsArrayList.add(text!!.toString())
                    }

                    binding.recyclerView.adapter = ChatsRecyclerViewAdapter(textsArrayList)
                    binding.recyclerView.layoutManager = LinearLayoutManager(baseContext)

                }

                override fun onCancelled(error: DatabaseError) {
                    Log.d(ContentValues.TAG, "Failed to read value.", error.toException())

                }

            })

        }
    }
}