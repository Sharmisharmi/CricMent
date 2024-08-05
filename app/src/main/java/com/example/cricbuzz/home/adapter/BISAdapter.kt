package com.example.cricbuzz.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cricbuzz.R
import com.example.cricbuzz.home.model.ArticleData
import com.example.cricbuzz.home.model.SourceData
import com.squareup.picasso.Picasso

class BISAdapter(context: Context, data: List<ArticleData>?) : RecyclerView.Adapter<BISAdapter.ViewHolder>() {

    private var context : Context
    var data:List<ArticleData>


    init {
        this.context=context
        this.data = data!!

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BISAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.bis_list,parent,false))
    }

    override fun onBindViewHolder(holder: BISAdapter.ViewHolder, position: Int) {

        holder.newsTitle.text=data[position].title.toString()

        if (data[position].urlToImage != null ||  data[position].urlToImage != ""){
            Picasso.get().load(data[position].urlToImage).into(holder.newsImage)
        }

    }


    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){

        var newsTitle : TextView = itemView.findViewById(R.id.newsTitle)
        var newsImage : ImageView = itemView.findViewById(R.id.newsImage)
//        var sportsCard : CardView = itemView.findViewById(R.id.sportsCard)

    }


}