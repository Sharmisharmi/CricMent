package com.example.cricbuzz.players.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.cricbuzz.R
import com.example.cricbuzz.players.PlayerInfo

class PlayersListAdapter (context: Context, i: Int) : RecyclerView.Adapter<PlayersListAdapter.ViewHolder>() {

    private var context : Context
    var i:Int


    init {
        this.context=context
        this.i = i

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.players_list,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

//        holder.title.text=data[position].cat_name.toString()
//
//
//        Picasso.get().load(data[position].image_url).into(holder.image)
//
        holder.playerCard.setOnClickListener {
            context.startActivity(Intent(context, PlayerInfo::class.java))
        }

//        holder.news_desc.setSelected(true);
    }


    override fun getItemCount(): Int {
        return i
    }

    inner class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){

//        var news_desc : TextView = itemView.findViewById(R.id.news_desc)
//        var image : ImageView = itemView.findViewById(R.id.images)
        var playerCard : CardView = itemView.findViewById(R.id.playerCard)

    }


}