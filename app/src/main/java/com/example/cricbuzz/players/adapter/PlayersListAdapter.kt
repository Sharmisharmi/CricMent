package com.example.cricbuzz.players.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.cricbuzz.R
import com.example.cricbuzz.players.PlayerInfo
import com.example.cricbuzz.players.model.PlayerList
import com.squareup.picasso.Picasso

class PlayersListAdapter(context: Context, data: MutableList<PlayerList>?, status: String) : RecyclerView.Adapter<PlayersListAdapter.ViewHolder>() {

    private var context : Context
    var data:MutableList<PlayerList>
    var status:String


    init {
        this.context=context
        this.data = data!!
        this.status = status!!

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.players_list,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if (status.equals("Batsman")){
            if (data[position].role.toString().equals("Batsman")){
                holder.playerName.text=data[position].name.toString()

                if (data[position].role.toString().contains("Allrounder")){
                    holder.roleTXT.text = "Allrounder"
                }else {
                    holder.roleTXT.text=data[position].role.toString()
                }


                Picasso.get().load(data[position].playerImg).into(holder.teamImage)

            }
        }else if (status.equals("Bowler")){
            if (data[position].role.toString().equals("Bowler")){
                holder.playerName.text=data[position].name.toString()

                if (data[position].role.toString().contains("Allrounder")){
                    holder.roleTXT.text = "Allrounder"
                }else {
                    holder.roleTXT.text=data[position].role.toString()
                }


                Picasso.get().load(data[position].playerImg).into(holder.teamImage)

            }
        }else if (status.equals("All")){

                holder.playerName.text=data[position].name.toString()

                if (data[position].role.toString().contains("Allrounder")){
                    holder.roleTXT.text = "Allrounder"
                }else {
                    holder.roleTXT.text=data[position].role.toString()
                }


                Picasso.get().load(data[position].playerImg).into(holder.teamImage)

        }


        holder.playerCard.setOnClickListener {
            context.startActivity(Intent(context, PlayerInfo::class.java).putExtra("PlayerInfo",data[position]))

        }

    }


    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){

        var playerName : TextView = itemView.findViewById(R.id.playerName)
        var roleTXT : TextView = itemView.findViewById(R.id.roleTXT)
        var teamImage : ImageView = itemView.findViewById(R.id.teamImage)
        var playerCard : CardView = itemView.findViewById(R.id.playerCard)

    }


}