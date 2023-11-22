package com.example.cricbuzz.match.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cricbuzz.R
import com.example.cricbuzz.home.NewsLayout
import com.example.cricbuzz.match.model.MatchesData
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class PlayingXIAdapter(context: Context, data: List<MatchesData>?) : RecyclerView.Adapter<PlayingXIAdapter.ViewHolder>() {

    private var context : Context
    var data:List<MatchesData>


    init {
        this.context=context
        this.data = data!!

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayingXIAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.playingxi_list,parent,false))
    }

    override fun onBindViewHolder(holder: PlayingXIAdapter.ViewHolder, position: Int) {

//        holder.player.text=data[position].shortName.toString()
//
//        when(data[position].role.toString()){
//            "all"-> holder.role.text="All rounder"
//            "bowl"-> holder.role.text="Bowler"
//            "bat"-> holder.role.text="Batsman"
//        }

//
//
//        Picasso.get().load(data[position].image_url).into(holder.image)
//


    }


    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){

        var player : TextView = itemView.findViewById(R.id.player)
        var playerImg : CircleImageView = itemView.findViewById(R.id.playerImg)
        var role : TextView = itemView.findViewById(R.id.role)


    }


}