package com.example.cricbuzz.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cricbuzz.R

class BISAdapter (context: Context, i: Int) : RecyclerView.Adapter<BISAdapter.ViewHolder>() {

    private var context : Context
    var i:Int


    init {
        this.context=context
        this.i = i

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BISAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.bis_list,parent,false))
    }

    override fun onBindViewHolder(holder: BISAdapter.ViewHolder, position: Int) {

//        holder.title.text=data[position].cat_name.toString()
//
//
//        Picasso.get().load(data[position].image_url).into(holder.image)
//
//        holder.sportsCard.setOnClickListener {
//            context.startActivity(Intent(context,CategoryDetailsActivity::class.java).putExtra("category_id",data[position].cat_id))
//        }

//        holder.news_desc.setSelected(true);
    }


    override fun getItemCount(): Int {
        return i
    }

    inner class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){

//        var news_desc : TextView = itemView.findViewById(R.id.news_desc)
//        var image : ImageView = itemView.findViewById(R.id.images)
//        var sportsCard : CardView = itemView.findViewById(R.id.sportsCard)

    }


}