package com.example.firechatapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class ChatsRecyclerViewAdapter(var textList:ArrayList<String>):RecyclerView.Adapter<ChatViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.chat_custom_view,parent,false)
        return ChatViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
//        val currentText = textList[position]
        holder.chatText.text = textList[position]
    }

    override fun getItemCount(): Int {
        return textList.size
    }


}
class ChatViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
   var chatText = itemView.findViewById<TextView>(R.id.tvChatText)
    var textCard = itemView.findViewById<CardView>(R.id.cardText)
}