package com.example.cricbuzz.match.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cricbuzz.R
import com.example.cricbuzz.players.model.PlayerData
import com.example.cricbuzz.players.model.PlayerList
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class PlayingXIAdapter(context: Context, data: MutableList<PlayerList>?) : RecyclerView.Adapter<PlayingXIAdapter.ViewHolder>() {

    private var context : Context
    var data:List<PlayerList>


    init {
        this.context=context
        this.data = data!!

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayingXIAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.playingxi_list,parent,false))
    }

    override fun onBindViewHolder(holder: PlayingXIAdapter.ViewHolder, position: Int) {

        holder.player.text=data[position].name.toString()
        holder.role.text = data[position].role.toString()

        if (data[position].playerImg != null){
            Picasso.get().load(data[position].playerImg).into(holder.playerImg)
        }



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