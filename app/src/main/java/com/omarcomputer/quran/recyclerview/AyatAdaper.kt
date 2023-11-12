package com.omarcomputer.quran.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.omarcomputer.quran.R
import com.omarcomputer.quran.model.Ayat

class AyatAdaper(val itemList : List<Ayat>,val listener :Listener) : RecyclerView.Adapter<AyatAdaper.ViewHolder>(){
    private  var currentIndex = 0;
    interface Listener{
        fun onItemClick(position: Int,view: View)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView :View  = LayoutInflater.from(parent.context).inflate(R.layout.aya_item_layout,parent,false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ayaItem : Ayat = itemList[position]
        val number = ayaItem.id.toInt() % 1000
        holder.ayaId.text = number.toString()
        holder.ayaText.text = ayaItem.text

    }
   inner class ViewHolder(itemView : View) :RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition,itemView)
            }
        }
        var ayaId : TextView = itemView.findViewById(R.id.ayaId)
        var ayaText : TextView = itemView.findViewById(R.id.ayaText)

    }
}