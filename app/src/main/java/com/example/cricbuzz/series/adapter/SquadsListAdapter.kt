package com.example.cricbuzz.series.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cricbuzz.R
import com.example.cricbuzz.players.model.PlayerData
import com.squareup.picasso.Picasso

class SquadsListAdapter(context: Context, data: List<PlayerData>?, onClickListener: squadsonClickListener) : RecyclerView.Adapter<SquadsListAdapter.ViewHolder>() {

    private var context : Context
    private var data:List<PlayerData>
    var onClickListener: squadsonClickListener


    init {
        this.context=context
        this.data = data!!
        this.onClickListener = onClickListener

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.squads_list,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.teamName.text=data[position].shortname.toString()
        holder.playerCount.text= data[position].players!!.size.toString() +" Players"
//
//
        Picasso.get().load(data[position].img).into(holder.image)
//


        holder.poolLL.setOnClickListener {
            onClickListener.teams(data[position])
        }

    }


    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
//
        var teamName : TextView = itemView.findViewById(R.id.teamName)
        var playerCount : TextView = itemView.findViewById(R.id.playerCount)
        var image : ImageView = itemView.findViewById(R.id.image)
        var poolLL : LinearLayout = itemView.findViewById(R.id.poolLL)

    }

    interface squadsonClickListener{
        fun teams(i: PlayerData)
    }

}