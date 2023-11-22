package com.example.cricbuzz.home.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.cricbuzz.R
import com.example.cricbuzz.home.NewsLayout

class LatestNewsAdapter(context: Context, i: Int) : RecyclerView.Adapter<LatestNewsAdapter.ViewHolder>() {

    private var context : Context
   var i:Int


    init {
        this.context=context
        this.i = i

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatestNewsAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.latest_news_list,parent,false))
    }

    override fun onBindViewHolder(holder: LatestNewsAdapter.ViewHolder, position: Int) {

//        holder.title.text=data[position].cat_name.toString()
//
//
//        Picasso.get().load(data[position].image_url).into(holder.image)
//
        holder.newsLL.setOnClickListener {
            context.startActivity(Intent(context, NewsLayout::class.java))
        }

        holder.news_desc.setSelected(true);
    }


    override fun getItemCount(): Int {
        return i
    }

    inner class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){

        var news_desc : TextView = itemView.findViewById(R.id.news_desc)
//        var image : ImageView = itemView.findViewById(R.id.images)
        var newsLL : LinearLayout = itemView.findViewById(R.id.newsLL)

    }


}