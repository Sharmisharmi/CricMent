package com.example.cricbuzz.home.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cricbuzz.R
import com.example.cricbuzz.home.NewsLayout
import com.example.cricbuzz.home.model.ArticleData
import com.squareup.picasso.Picasso

class LatestNewsAdapter(context: Context, data: List<ArticleData>?) : RecyclerView.Adapter<LatestNewsAdapter.ViewHolder>() {

    private var context : Context
   var data:List<ArticleData>


    init {
        this.context=context
        this.data= data!!

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatestNewsAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.latest_news_list,parent,false))
    }

    override fun onBindViewHolder(holder: LatestNewsAdapter.ViewHolder, position: Int) {

        holder.newsTitle.text=data[position].title.toString()
        holder.news_desc.text=data[position].description.toString()
        holder.newsContent.text=data[position].content.toString()


        Log.d("News_Image", "onBindViewHolder: "+data[position].urlToImage)
        if (data[position].urlToImage != null){
            Picasso.get().load(data[position].urlToImage).into(holder.newsImage)
        }else{
            holder.newsImage.setImageResource(R.drawable.no_image)
        }

        holder.newsLL.setOnClickListener {
            context.startActivity(Intent(context, NewsLayout::class.java).putExtra("NewsData",data[position]))
        }

        holder.newsContent.setSelected(true);
    }


    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){

        var news_desc : TextView = itemView.findViewById(R.id.news_desc)
        var newsContent : TextView = itemView.findViewById(R.id.newsContent)
        var newsTitle : TextView = itemView.findViewById(R.id.newsTitle)
        var newsImage : ImageView = itemView.findViewById(R.id.newsImage)
        var newsLL : LinearLayout = itemView.findViewById(R.id.newsLL)

    }


}